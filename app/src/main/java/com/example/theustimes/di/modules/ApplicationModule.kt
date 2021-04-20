package com.example.theustimes.di.modules

import com.example.theustimes.app.MyApplication
import com.example.theustimes.network.NetworkConfiguration
import com.example.theustimes.network.NetworkProvider
import com.example.theustimes.news.newsdetails.remote.NewsDetailsApiService
import com.example.theustimes.news.newsdetails.repository.NewsDetailsRepository
import com.example.theustimes.news.newsdetails.repository.NewsDetailsRepositoryImpl
import com.example.theustimes.news.newsdetails.usecase.NewsDetailsUseCase
import com.example.theustimes.news.newsdetails.usecase.NewsDetailsUseCaseImpl
import com.example.theustimes.news.newslisting.remote.NewsListingApiService
import com.example.theustimes.news.newslisting.repository.NewsListingRepository
import com.example.theustimes.news.newslisting.repository.NewsListingRepositoryImpl
import com.example.theustimes.news.newslisting.usecase.NewsListingUseCase
import com.example.theustimes.news.newslisting.usecase.NewsListingUseCaseImpl
import com.example.theustimes.utils.Util
import com.example.theustimes.errorProvider.ErrorProvider
import com.example.theustimes.errorProvider.ErrorProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideErrorProvider(): ErrorProvider {
        return ErrorProviderImpl()
    }

    @Singleton
    @Provides
    fun provideNewsListingUseCase(newsListingRepository: NewsListingRepository): NewsListingUseCase =
        NewsListingUseCaseImpl(newsListingRepository)

    @Singleton
    @Provides
    fun providesNewsListingRepository(newsListingApiService: NewsListingApiService): NewsListingRepository =
        NewsListingRepositoryImpl(newsListingApiService)

    @Singleton
    @Provides
    fun providesNewsListingApiService(
        networkProvider: NetworkProvider,
        networkConfiguration: NetworkConfiguration
    ): NewsListingApiService {
        return networkProvider.getApiInstance(
            NewsListingApiService::class.java,
            networkConfiguration.getBaseUrl()
        )
    }

    @Singleton
    @Provides
    fun provideNewsDetailsUseCase(
        newsDetailsRepository: NewsDetailsRepository
    ): NewsDetailsUseCase {
        return NewsDetailsUseCaseImpl(newsDetailsRepository)
    }

    @Singleton
    @Provides
    fun providesNewsDetailsRepository(
        newsDetailsApiService: NewsDetailsApiService
    ): NewsDetailsRepository {
        return NewsDetailsRepositoryImpl(newsDetailsApiService)
    }

    @Singleton
    @Provides
    fun providesNewsDetailsApiService(
        networkProvider: NetworkProvider,
        networkConfiguration: NetworkConfiguration
    ): NewsDetailsApiService {
        return networkProvider.getApiInstance(
            NewsDetailsApiService::class.java,
            networkConfiguration.getSocialEngineUrl()
        )
    }

    @Singleton
    @Provides
    fun provideUtil(): Util = Util()

}