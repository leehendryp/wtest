package com.leehendryp.wtest.presentation.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class ArticleListAdapter(
    private val articles: MutableList<ArticleItem>
) : RecyclerView.Adapter<BindingViewHolder>() {

    override fun getItemCount(): Int = articles.size

    override fun getItemViewType(position: Int): Int = getArticle(position).layoutId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            inflater, viewType, parent, false
        )
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        getArticle(position).bind(holder.binding)
        holder.binding.executePendingBindings()
    }

    fun update(newItems: List<ArticleItem>) {
        this.articles.clear()
        this.articles.addAll(newItems)
        notifyDataSetChanged()
    }

    private fun getArticle(position: Int): ArticleItem = articles[position]
}

class BindingViewHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root)