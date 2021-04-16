package com.example.theustimes.news.newslisting.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theustimes.news.models.Articles
import com.example.theustimes.news.newslisting.usecase.NewsListingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.theustimes.utils.Result
import com.example.theustimes.utils.errorProvider.ErrorProvider

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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _resultNewsList.postValue(Result.Loading)
                val response = newsListingUseCase.getTopNews(country, apiKey)
                _resultNewsList.postValue(Result.Success(response.articles))
            } catch (exception: Exception) {
                _resultNewsList.postValue(Result.Error(errorProvider.getErrorMessage(exception)))
            }
        }
    }
}