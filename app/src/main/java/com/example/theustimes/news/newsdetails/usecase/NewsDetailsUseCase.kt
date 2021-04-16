package com.example.theustimes.news.newsdetails.usecase

import com.example.theustimes.news.models.LikesAndCommentsApiResponse
import com.example.theustimes.news.newsdetails.repository.NewsDetailsRepository

interface NewsDetailsUseCase{
    suspend fun getComments(url: String): LikesAndCommentsApiResponse
    suspend fun getLikes(url: String): LikesAndCommentsApiResponse
}

class NewsDetailsUseCaseImpl(
    private val newsDetailsRepository: NewsDetailsRepository
): NewsDetailsUseCase {

    override suspend fun getComments(url: String): LikesAndCommentsApiResponse {
        return newsDetailsRepository.getComments(url)
    }

    override suspend fun getLikes(url: String): LikesAndCommentsApiResponse {
        return newsDetailsRepository.getLikes(url)
    }

}