package com.example.theustimes.news.newsdetails.remote

import com.example.theustimes.news.models.LikesAndCommentsApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface NewsDetailsApiService {

    @GET("comments/{articleId}")
    suspend fun getComments(
        @Path("articleId") articleId: String
    ): LikesAndCommentsApiResponse

    @GET("likes/{articleId}")
    suspend fun getLikes(
        @Path("articleId") articleId: String
    ): LikesAndCommentsApiResponse

}