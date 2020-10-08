package com.terms.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.terms.R
import com.terms.data.model.Definition
import kotlinx.android.synthetic.main.dialog_term_detail.*

class TermDetailDialog : DialogFragment() {
    companion object {
        const val ARG_WORD = "arg_word"
        fun instance(definition: Definition): TermDetailDialog {
            val wordDetailDialog = TermDetailDialog()
            val arguments = Bundle()
            arguments.putParcelable(ARG_WORD, definition)
            wordDetailDialog.arguments = arguments
            return wordDetailDialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_term_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val word = arguments?.getParcelable<Definition>(ARG_WORD)
        word?.apply {
            exampleTextView.text = this.example
        }
        btnOkay.setOnClickListener { dismiss() }

    }
}