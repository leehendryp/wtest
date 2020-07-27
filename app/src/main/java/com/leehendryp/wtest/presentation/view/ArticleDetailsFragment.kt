package com.leehendryp.wtest.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.leehendryp.wtest.core.CustomApplication
import com.leehendryp.wtest.databinding.ArticleDetailsFragmentBinding
import com.leehendryp.wtest.presentation.viewmodel.ArticleDetailsViewModel
import javax.inject.Inject

class ArticleDetailsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ArticleDetailsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ArticleDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = ArticleDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectDependencies()
    }

    private fun injectDependencies() {
        (activity?.application as CustomApplication).appComponent.inject(this)
    }
}
