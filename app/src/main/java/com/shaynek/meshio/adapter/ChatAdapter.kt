package com.shaynek.meshio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.meshio.R
import com.shaynek.meshio.rx.MessageEvent

class ChatAdapter(var dataset: MutableList<MessageEvent>) :
        RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageTextView = view.findViewById<TextView>(R.id.chat_item_message_textview)!!
        val nameTextView = view.findViewById<TextView>(R.id.chat_item_name_textview)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = dataset[position]
        holder.messageTextView.text = message.message
        holder.nameTextView.text = if (message.me) "You" else "Anonymous User"
    }

    override fun getItemCount(): Int = dataset.size
}