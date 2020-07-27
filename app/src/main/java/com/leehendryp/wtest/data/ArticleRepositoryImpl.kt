package com.leehendryp.wtest.data

import com.leehendryp.wtest.core.utils.NetworkUtils
import com.leehendryp.wtest.core.utils.coTryCatch
import com.leehendryp.wtest.data.remote.RemoteDataSource
import com.leehendryp.wtest.domain.Article
import com.leehendryp.wtest.domain.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val remoteDataSource: RemoteDataSource
) : ArticleRepository {
    override suspend fun getArticlesAt(page: Int): List<Article> =
        coTryCatch {
            if (networkUtils.isInternetAvailable())
                remoteDataSource.getArticlesAt(page)
            else throw Throwable()
            // FIXME Lee July 26, 2020: this throwable is a placeholder for the local source call
        }
}