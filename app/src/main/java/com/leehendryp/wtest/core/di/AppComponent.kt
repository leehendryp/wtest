package com.leehendryp.wtest.core.di

import android.content.Context
import com.leehendryp.wtest.core.BaseFragment
import com.leehendryp.wtest.data.di.DataModule
import com.leehendryp.wtest.presentation.view.ArticleDetailsFragment
import com.leehendryp.wtest.presentation.view.ArticleDetailsFragment_MembersInjector
import com.leehendryp.wtest.presentation.view.ArticleListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: ArticleListFragment)
    fun inject(fragment: ArticleDetailsFragment)
}