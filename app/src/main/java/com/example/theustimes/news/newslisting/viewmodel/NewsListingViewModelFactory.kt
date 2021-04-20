package com.example.theustimes.news.newslisting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.theustimes.news.newslisting.usecase.NewsListingUseCase
import com.example.theustimes.errorProvider.ErrorProvider
import javax.inject.Inject

class NewsListingViewModelFactory @Inject constructor(
    private val newsListingUseCase: NewsListingUseCase,
    private val errorProvider: ErrorProvider
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListingViewModel::class.java)) {
            return NewsListingViewModel(newsListingUseCase, errorProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}