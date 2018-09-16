package com.shaynek.meshio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.meshio.R
import com.shaynek.meshio.adapter.ProAdapter
import io.left.rightmesh.id.MeshId
import kotlinx.android.synthetic.main.fragment_pro.*

class ProFragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var cache: MutableList<MeshId>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_pro, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        cache = (activity as MainActivity).userList
        viewManager = LinearLayoutManager(activity)
        viewAdapter = ProAdapter(cache)
        pro_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pro_update_button.setOnClickListener { viewAdapter.notifyDataSetChanged() }
        pro_update_settings.setOnClickListener { (activity as MainActivity).meshManager.showSettingsActivity() }
    }
}