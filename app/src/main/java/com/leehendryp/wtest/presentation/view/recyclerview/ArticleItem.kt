package com.leehendryp.wtest.presentation.view.recyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

data class ArticleItem(val data: Any, @LayoutRes val layoutId: Int, val variableId: Int) {
    fun bind(binding: ViewDataBinding) {
        binding.setVariable(variableId, data)
    }
}

