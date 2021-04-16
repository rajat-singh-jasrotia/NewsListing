package com.example.theustimes.news.newslisting.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.theustimes.R
import com.example.theustimes.databinding.ItemArticleDataBinding
import com.example.theustimes.news.models.Articles


class NewsListAdapter(
    private var list: MutableList<Articles> = mutableListOf(),
    private var newsItemClickListener: NewsItemClickListener?
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    // ViewHolder class
    class ViewHolder(
        val itemArticleDataBinding: ItemArticleDataBinding
    ) : RecyclerView.ViewHolder(itemArticleDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemArticleDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_list_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemArticles = list[position]
        holder.itemArticleDataBinding.itemArticles = itemArticles
        holder.itemArticleDataBinding.callbackOnItemClick = newsItemClickListener
        holder.itemArticleDataBinding.executePendingBindings()
    }

    fun updateAll(data: List<Articles>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}