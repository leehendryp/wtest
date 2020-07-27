package com.leehendryp.wtest.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.leehendryp.wtest.BR
import com.leehendryp.wtest.R
import com.leehendryp.wtest.core.*
import com.leehendryp.wtest.databinding.ArticleListFragmentBinding
import com.leehendryp.wtest.domain.Article
import com.leehendryp.wtest.presentation.view.recyclerview.*
import com.leehendryp.wtest.presentation.viewmodel.ArticleListState
import com.leehendryp.wtest.presentation.viewmodel.ArticleListState.*
import com.leehendryp.wtest.presentation.viewmodel.ArticleListViewModel
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class ArticleListFragment : BaseFragment() {
    companion object {
        private const val ANIM_LENGTH: Long = 700
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ArticleListViewModel? by viewModels { viewModelFactory }

    private var _binding: ArticleListFragmentBinding? = null

    // Lee July 27, 2020: double-banged backing property, as recommended in Android developer docs
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        _binding = ArticleListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectDependencies()
        observeViewModel()
        initRecyclerView()
    }

    private fun injectDependencies() {
        (activity?.application as CustomApplication).appComponent.inject(this)
    }

    private fun initRecyclerView() {
        initArticleRecyclerView(binding.recyclerviewArticles) { viewModel?.fetchArticles() }
    }

    private fun observeViewModel() {
        viewModel?.state?.observe(viewLifecycleOwner, Observer(::updateUI))
    }

    private fun updateUI(state: ArticleListState) {
        toggleLoading(state)
        when (state) {
            is Success -> update(binding.recyclerviewArticles, getArticleItems())
            is Error -> showErrorDialog(state)
        }
    }

    private fun toggleLoading(state: ArticleListState) {
        binding.containerLoadingWheel.apply {
            if (state == Loading) fadeIn(ANIM_LENGTH) else vanish(ANIM_LENGTH)
        }
    }

    private fun getArticleItems(): MutableList<ArticleItem> {
        val list = mutableListOf<ArticleItem>()
        (viewModel?.state?.value as Success).data.map { list.add(it.toArticleItem()) }
        return list
    }

    private fun showErrorDialog(state: Error) {
        when (state.error) {
            is MyBadException -> showErrorDialog(message = R.string.error_my_bad)
            is ServiceInstabilityException -> showErrorDialog(message = R.string.error_service_instability)
            is TimeoutException -> showErrorDialog(message = R.string.error_no_connection)
            is IOException -> showErrorDialog(message = R.string.error_no_connection)
            else -> showErrorDialog()
        }
    }

    private fun Article.toArticleItem() = ArticleItem(
        data = this,
        layoutId = R.layout.article_item,
        variableId = BR.article
    )

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
