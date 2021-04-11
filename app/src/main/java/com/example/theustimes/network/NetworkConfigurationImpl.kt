package com.example.theustimes.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConfigurationImpl @Inject constructor(): NetworkConfiguration {

    companion object {
        private const val BASE_URL = "https://newsapi.org/"
        private const val SOCIAL_ENGINE_URL = "https://cn-news-info-api.herokuapp.com/"
        const val top_headlines = "v2/top-headlines"
        const val likes = "https://cn-news-info-api.herokuapp.com/likes/"
        const val comments = "https://cn-news-info-api.herokuapp.com/comments/"
    }

    override fun getBaseUrl(): String {
        return BASE_URL
    }

    override fun getSocialEngineUrl(): String {
        return SOCIAL_ENGINE_URL
    }

}