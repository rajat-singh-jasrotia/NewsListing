package com.example.theustimes.news.newslisting.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theustimes.news.models.Articles
import com.example.theustimes.news.newslisting.usecase.NewsListingUseCase
import com.example.theustimes.utils.Result
import kotlinx.coroutines.launch
import com.example.theustimes.errorProvider.ErrorProvider

class NewsListingViewModel(
    private val newsListingUseCase: NewsListingUseCase,
    private val errorProvider: ErrorProvider
    ) : ViewModel() {

    val errorVisibility: ObservableField<Boolean> = ObservableField(false)
    val errorMsg: ObservableField<String> = ObservableField("")
    val swipeLoadingVisibility: ObservableField<Boolean> = ObservableField(false)

    private val _resultNewsList = MutableLiveData<Result<List<Articles>>>()

    var progressBarState: ObservableField<Boolean> = ObservableField(true)
    var recyclerViewState: ObservableField<Boolean> = ObservableField(false)

    val resultNewsList: LiveData<Result<List<Articles>>>
        get() = _resultNewsList

    fun fetchNewsData(country: String, apiKey: String) {
        viewModelScope.launch {
            try {
                _resultNewsList.postValue(Result.loading())
                val response = newsListingUseCase.getTopNews(country, apiKey)
                _resultNewsList.postValue(Result.success(response.articles))
            } catch (exception: Exception) {
                _resultNewsList.postValue(Result.error(errorProvider.getErrorMessage(exception)))
            }
        }
    }
}