package com.shaynek.meshio.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.shaynek.meshio.R
import com.shaynek.meshio.rx.MessageEvent
import com.shaynek.meshio.rx.RxBus
import io.left.rightmesh.android.AndroidMeshManager
import io.left.rightmesh.id.MeshId
import io.left.rightmesh.mesh.MeshManager
import io.left.rightmesh.mesh.MeshManager.DATA_RECEIVED
import io.left.rightmesh.mesh.MeshManager.PEER_CHANGED
import io.left.rightmesh.mesh.MeshStateListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MeshStateListener {

    val meshPort = 4567
    val pattern = "abc"
    val superpeerUrl = "207.23.201.120"

    val userList: MutableList<MeshId> = mutableListOf()
    lateinit var meshManager: AndroidMeshManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Wire up navigation controller for fragments and BottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment)
        bottom_navigation_view.setupWithNavController(navController)

        meshManager = AndroidMeshManager.getInstance(this, this, pattern, superpeerUrl)
    }

    override fun meshStateChanged(p0: MeshId?, p1: Int) {
        if (p1 == MeshStateListener.SUCCESS) {
            meshManager.bind(meshPort)
            meshManager.on(PEER_CHANGED, this::onPeerChanged)
            meshManager.on(DATA_RECEIVED, this::onDataReceived)
        }
    }

    private fun onPeerChanged(e: MeshManager.RightMeshEvent) {
        val event = e as MeshManager.PeerChangedEvent
        event.peerUuid.let {
            if (!userList.contains(it)) userList.add(it)
        }
    }

    private fun onDataReceived(e: MeshManager.RightMeshEvent) {
        val event = e as MeshManager.DataReceivedEvent
        runOnUiThread {
            RxBus.publish(
                    MessageEvent(
                            String(event.data),
                            event.peerUuid.toString(),
                            event.peerUuid == meshManager.uuid))
        }
    }

    fun sendMessage(message: String) {
        val iterator = userList.iterator()
        iterator.forEach { meshManager.sendDataReliable(it, meshPort, message.toByteArray()) }
    }
}
