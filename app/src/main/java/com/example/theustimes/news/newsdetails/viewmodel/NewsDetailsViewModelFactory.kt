package com.example.theustimes.news.newsdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.theustimes.news.newsdetails.usecase.NewsDetailsUseCase
import com.example.theustimes.errorProvider.ErrorProvider
import javax.inject.Inject

class NewsDetailsViewModelFactory @Inject constructor(
    private val newsDetailsUseCase: NewsDetailsUseCase,
    private val errorProvider: ErrorProvider
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
            return NewsDetailsViewModel(newsDetailsUseCase, errorProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}