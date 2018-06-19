package com.alfianlosari.graphql_github

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val repoAdapter = RepositoryRecylcerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = repoAdapter
        observeTrendingRepositories()
    }

    private fun observeTrendingRepositories() {
        ViewModelProviders.of(this).get(TrendingRepositoriesViewModel::class.java)
                .reposResult
                .observe(this, Observer {
                    val repos = it?.first
                    if (repos != null) {
                        repoAdapter.updateRepos(repos)
                    }
                })
    }
}
