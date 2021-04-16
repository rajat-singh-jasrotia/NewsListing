package com.example.theustimes.news.newslisting.repository

import com.example.theustimes.news.models.NewsListingApiResponse
import com.example.theustimes.news.newslisting.remote.NewsListingApiService

interface NewsListingRepository {
    /**
     * method to get top US news
     */
    suspend fun getTopNews(
        country: String, apiKey: String
    ): NewsListingApiResponse

}

class NewsListingRepositoryImpl(
    private val newsListingApiService: NewsListingApiService
): NewsListingRepository {

    override suspend fun getTopNews(country: String, apiKey: String): NewsListingApiResponse {
        return newsListingApiService.getTopNews(
            country = country,
            apiKey = apiKey
        )
    }

}