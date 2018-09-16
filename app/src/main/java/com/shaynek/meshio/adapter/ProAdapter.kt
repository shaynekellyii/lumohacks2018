package com.shaynek.meshio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.meshio.R
import io.left.rightmesh.id.MeshId

class ProAdapter(private val dataset: MutableList<MeshId>) :
        RecyclerView.Adapter<ProAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView = view.findViewById<TextView>(R.id.pro_item_name_textview)
        val titleTextView = view.findViewById<TextView>(R.id.pro_item_title_textview)
        val descriptionTextView = view.findViewById<TextView>(R.id.pro_item_description_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pro_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pro = dataset[position]
        holder.nameTextView.text = pro.toString()
        holder.titleTextView.text = pro.toString()
        holder.descriptionTextView.text = pro.toString()
    }

    override fun getItemCount(): Int = dataset.size
}