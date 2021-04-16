package com.example.theustimes.news.newslisting.views.adapter

import com.example.theustimes.news.models.Articles

interface NewsItemClickListener {
    fun onNewsItemClicked(article: Articles)
}