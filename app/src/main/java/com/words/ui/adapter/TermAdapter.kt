package com.words.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.words.R
import com.words.data.model.Definition
import com.words.ui.adapter.listener.TermClickListener
import com.words.ui.adapter.viewholder.TermViewHolder

class TermAdapter constructor(val definitions: MutableList<Definition>, private val termClickListener: TermClickListener) :
    RecyclerView.Adapter<TermViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TermViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_term, parent, false))

    override fun getItemCount() = definitions.size

    override fun onBindViewHolder(holder: TermViewHolder, position: Int) {
        holder.bindItem(definitions[position])
        holder.itemView.setOnClickListener {
            termClickListener.onClick(definitions[holder.adapterPosition])
        }
    }
}