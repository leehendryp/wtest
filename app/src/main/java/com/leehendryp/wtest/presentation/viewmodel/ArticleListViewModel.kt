package com.leehendryp.wtest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leehendryp.wtest.domain.ArticleRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(private val repo: ArticleRepository) : ViewModel() {
    private val _state by lazy {
        MutableLiveData<ArticleListState>().apply { toDefault() }
    }
    val state: LiveData<ArticleListState> = _state

    private var nextPage: Int? = 1

    init {
        fetchArticles()
    }

    fun fetchArticles() {
        nextPage?.let {
            launchDataLoad {
                with(repo.getArticlesAt(it)) {
                    _state.toSuccess(this)
                }
                nextPage = it + 1
            }
        }
    }

    private fun resetPage() {
        nextPage = 1
    }

    private fun <T> launchDataLoad(block: suspend () -> T): Job {
        _state.toLoading()
        return viewModelScope.launch {
            try {
                block()
            } catch (error: Throwable) {
                _state.toError(error)
            } finally {
                _state.toDefault()
            }
        }
    }
}