package com.alfianlosari.graphql_github

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import GetLatestTrendingRepositoriesInLastWeekQuery
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryRecylcerViewAdapter: RecyclerView.Adapter<RepositoryRecylcerViewAdapter.RepositoryViewHolder>()  {

    private var repositories: List<GetLatestTrendingRepositoriesInLastWeekQuery.Edge> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.repository_item, parent, false)
        return RepositoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val edge = repositories[position]
        val node = edge.node()
        if (node != null) {
            holder.bindRepository(node)
        }
    }

    fun updateRepos(repos: List<GetLatestTrendingRepositoriesInLastWeekQuery.Edge>) {
        repositories = repos
        notifyDataSetChanged()
    }


    class RepositoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindRepository(repo: GetLatestTrendingRepositoriesInLastWeekQuery.Node) {
            if (repo is GetLatestTrendingRepositoriesInLastWeekQuery.AsRepository) {
                itemView.name.text = repo.name()
                itemView.owner.text = repo.owner().login()
                itemView.description.text = repo.description()
                itemView.star_count.text = "${repo.stargazers().totalCount()} ✭"
                itemView.language.text = repo.primaryLanguage()?.name()
            }
        }
    }

}