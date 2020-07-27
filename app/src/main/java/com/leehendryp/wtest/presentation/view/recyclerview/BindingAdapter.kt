package com.leehendryp.wtest.presentation.view.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leehendryp.wtest.domain.Article

@BindingAdapter("articles")
fun initArticleRecyclerView(
    recyclerView: RecyclerView,
    onScrollToEnd: () -> Unit
) {
    var adapter = (recyclerView.adapter as? ArticleListAdapter)
    if (adapter == null) {
        adapter = ArticleListAdapter(mutableListOf())
        recyclerView.apply {
            this.adapter = adapter
            addOnScrollListener(EndlessOnScrollListener(onScrollToEnd))
        }
    }
}

@BindingAdapter("articles")
fun update(recyclerView: RecyclerView, ArticleItems: List<ArticleItem>) {
    recyclerView.apply {
        (adapter as? ArticleListAdapter)?.update(ArticleItems)
    }
}