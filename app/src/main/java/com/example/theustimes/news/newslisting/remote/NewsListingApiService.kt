package com.example.theustimes.news.newslisting.remote

import com.example.theustimes.news.models.NewsListingApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsListingApiService {

    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsListingApiResponse

}