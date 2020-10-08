package com.words.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.words.R
import com.words.data.model.Definition
import com.words.data.remote.WebServices
import com.words.data.repository.DefinitionRepositoryImpl
import com.words.ui.adapter.TermAdapter
import com.words.ui.adapter.listener.TermClickListener
import com.words.ui.dialog.TermDetailDialog
import com.words.viewmodel.MainViewModel
import com.words.viewmodel.factory.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var termAdapter: TermAdapter
    private val termClickListener: TermClickListener = object : TermClickListener {
        override fun onClick(definition: Definition) {
            val wordDetailDialog = TermDetailDialog.instance(definition)
            wordDetailDialog.show(supportFragmentManager, "WORD_DETAIL")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(DefinitionRepositoryImpl(WebServices.instance))
        )
            .get(MainViewModel::class.java)




        viewModel.loadingState.observe(this, Observer {
            when (it) {
                is MainViewModel.LoadingState.LOADING -> displayProgressbar()
                is MainViewModel.LoadingState.SUCCESS -> displayList(it.definitions)
                is MainViewModel.LoadingState.ERROR -> displayMessageContainer(it.message)
                else -> displayMessageContainer("Unknown Error")
            }
        })


        btnRetry.setOnClickListener {
            viewModel.getDefinition(editTerm.text.toString())
        }
        btnSearch.setOnClickListener {
            viewModel.getDefinition(editTerm.text.toString())
        }
        btnSortByUpVotes.setOnClickListener {
            viewModel.sortByUpVotes()
        }
        btnSortByDownVotes.setOnClickListener {
            viewModel.sortByDownVotes()
        }

    }

    private fun displayProgressbar() {
        progressBar.visibility = View.VISIBLE
        definitionsRecycView.visibility = View.GONE
        messageContainer.visibility = View.GONE
    }

    private fun displayMessageContainer(message: String) {
        btnSortByUpVotes.visibility = View.GONE
        btnSortByDownVotes.visibility = View.GONE
        messageContainer.visibility = View.VISIBLE
        definitionsRecycView.visibility = View.GONE
        progressBar.visibility = View.GONE
        messageTextView.text = message
    }

    private fun displayList(definitions: List<Definition>) {
        btnSortByUpVotes.visibility = View.VISIBLE
        btnSortByDownVotes.visibility = View.VISIBLE
        termAdapter.definitions.clear()
        termAdapter.definitions.addAll(definitions)
        termAdapter.notifyDataSetChanged()
        messageContainer.visibility = View.GONE
        definitionsRecycView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        definitionsRecycView.layoutManager = LinearLayoutManager(this)
        termAdapter = TermAdapter(mutableListOf(), termClickListener)
        definitionsRecycView.adapter = termAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.getDefinition(editTerm.text.toString())
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }

}
