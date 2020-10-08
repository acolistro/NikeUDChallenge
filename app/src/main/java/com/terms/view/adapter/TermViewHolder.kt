package com.terms.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.terms.data.model.Definition
import kotlinx.android.synthetic.main.view_holder_term.view.*

class TermViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    fun bindItem(definition: Definition) {
        itemView.definitionTextView.text = definition.definition
        itemView.upVoteTextView.text = definition.thumbsUp.toString()
        itemView.downVoteTextView.text = definition.thumbsDown.toString()
    }
}