package com.example.theustimes.news.newsdetails.repository

import com.example.theustimes.news.models.LikesAndCommentsApiResponse
import com.example.theustimes.news.newsdetails.remote.NewsDetailsApiService

interface NewsDetailsRepository {
    suspend fun getComments(
        url: String
    ): LikesAndCommentsApiResponse

    suspend fun getLikes(
        url: String
    ): LikesAndCommentsApiResponse
}

class NewsDetailsRepositoryImpl(
    private val newsDetailsApiService: NewsDetailsApiService
) : NewsDetailsRepository {

    override suspend fun getComments(url: String): LikesAndCommentsApiResponse {
        return newsDetailsApiService.getComments(url)
    }

    override suspend fun getLikes(url: String): LikesAndCommentsApiResponse {
        return newsDetailsApiService.getLikes(url)
    }

}