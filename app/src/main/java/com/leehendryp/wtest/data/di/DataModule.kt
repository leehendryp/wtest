package com.leehendryp.wtest.data.di

import android.content.Context
import com.leehendryp.wtest.BuildConfig
import com.leehendryp.wtest.core.utils.NetworkUtils
import com.leehendryp.wtest.data.ArticleRepositoryImpl
import com.leehendryp.wtest.data.FeatThreeAPI
import com.leehendryp.wtest.data.FeatThreeAPI.Companion.BASE_URL
import com.leehendryp.wtest.data.remote.RemoteDataSource
import com.leehendryp.wtest.data.remote.RemoteDataSourceImpl
import com.leehendryp.wtest.domain.ArticleRepository
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {
    companion object {
        private const val TIMEOUT_LENGTH: Long = 5
        private const val DEBUG_TIMEOUT_LENGTH: Long = 30
    }

    @Singleton
    @Provides
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()

        client.apply {
            addInterceptor(httpLoggingInterceptor)
            connectTimeout(
                if (BuildConfig.DEBUG) DEBUG_TIMEOUT_LENGTH else TIMEOUT_LENGTH,
                TimeUnit.SECONDS
            )
        }
        return client.build()
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    }

    @Singleton
    @Provides
    fun provideFeatThreeApi(okHttpClient: OkHttpClient): FeatThreeAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FeatThreeAPI::class.java)

    @Provides
    fun provideRemoteDataSource(api: FeatThreeAPI): RemoteDataSource = RemoteDataSourceImpl(api)

    @Provides
    fun provideNetworkUtils(context: Context): NetworkUtils = NetworkUtils(context)

    @Singleton
    @Provides
    fun provideRepository(
        networkUtils: NetworkUtils,
        remoteDataSource: RemoteDataSource
    ): ArticleRepository = ArticleRepositoryImpl(networkUtils, remoteDataSource)
}