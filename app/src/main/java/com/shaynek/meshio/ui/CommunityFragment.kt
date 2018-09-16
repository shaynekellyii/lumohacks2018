package com.shaynek.meshio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.meshio.R
import com.shaynek.meshio.adapter.ChatAdapter
import com.shaynek.meshio.rx.MessageEvent
import com.shaynek.meshio.rx.RxBus
import kotlinx.android.synthetic.main.fragment_community.*

class CommunityFragment : Fragment() {

    private var messageList: MutableList<MessageEvent> = mutableListOf()

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_community, container, false)
        root.findViewById<Button>(R.id.community_send_button).setOnClickListener {
            (activity as MainActivity).sendMessage(community_message_edittext.text.toString())
            community_message_edittext.text = null
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewManager = LinearLayoutManager(activity)
        (viewManager as LinearLayoutManager).reverseLayout = true
        viewAdapter = ChatAdapter(messageList)
        community_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        RxBus.listen(MessageEvent::class.java)
                .subscribe({
            messageList.add(0, it)
            viewAdapter.notifyDataSetChanged()
        })
    }
}
