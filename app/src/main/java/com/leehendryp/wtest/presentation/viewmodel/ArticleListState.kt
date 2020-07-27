package com.leehendryp.wtest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.leehendryp.wtest.domain.Article
import com.leehendryp.wtest.presentation.viewmodel.ArticleListState.*

sealed class ArticleListState {
    object Default : ArticleListState()
    object Loading : ArticleListState()
    data class Success(val data: List<Article>) : ArticleListState()
    data class Error(val error: Throwable) : ArticleListState()
}

fun MutableLiveData<ArticleListState>.toDefault() {
    value = Default
}

fun MutableLiveData<ArticleListState>.toLoading() = postValue(Loading)

fun MutableLiveData<ArticleListState>.toSuccess(data: List<Article>) = postValue(Success(data))

fun MutableLiveData<ArticleListState>.toError(error: Throwable) = postValue(Error(error))