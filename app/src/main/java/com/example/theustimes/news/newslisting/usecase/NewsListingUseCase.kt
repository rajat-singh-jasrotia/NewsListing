package com.example.theustimes.news.newslisting.usecase

import com.example.theustimes.news.models.NewsListingApiResponse
import com.example.theustimes.news.newslisting.repository.NewsListingRepository

interface NewsListingUseCase {
    suspend fun getTopNews(country: String, apiKey: String): NewsListingApiResponse
}

class NewsListingUseCaseImpl(
    private val newsListingRepository: NewsListingRepository
): NewsListingUseCase {

    override suspend fun getTopNews(country: String, apiKey: String): NewsListingApiResponse {
        return newsListingRepository.getTopNews(country, apiKey)
    }

}