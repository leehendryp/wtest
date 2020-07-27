package com.leehendryp.wtest.data.remote

import com.leehendryp.wtest.core.MyBadException
import com.leehendryp.wtest.core.ServiceInstabilityException
import com.leehendryp.wtest.data.FeatThreeAPI
import com.leehendryp.wtest.domain.Article
import com.leehendryp.wtest.domain.retrieveArticles
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: FeatThreeAPI) : RemoteDataSource {
    override suspend fun getArticlesAt(page: Int): List<Article> =
        with(api.getArticlesAt(page = page)) {
            if (isSuccessful) body()!!.retrieveArticles()
            else throw throwable()
        }

    private fun <T> Response<T>.throwable(): Throwable {
        return with(code()) {
            when {
                this in 400..499 -> MyBadException(message())
                this >= 500 -> ServiceInstabilityException(message())
                else -> Throwable(message())
            }
        }
    }
}