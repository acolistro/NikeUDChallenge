package com.words.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.words.data.model.Definition
import kotlinx.android.synthetic.main.view_holder_term.view.*

class TermViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    fun bindItem(definition: Definition) {
        itemView.tvDefinition.text = definition.definition
        itemView.tvUpVote.text = definition.thumbsUp.toString()
        itemView.tvDownVote.text = definition.thumbsDown.toString()
    }
}