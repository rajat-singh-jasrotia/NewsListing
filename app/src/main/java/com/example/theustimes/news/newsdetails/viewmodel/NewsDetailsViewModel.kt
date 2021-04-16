package com.example.theustimes.news.newsdetails.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theustimes.news.models.Articles
import com.example.theustimes.news.models.LikesAndCommentsApiResponse
import com.example.theustimes.news.newsdetails.usecase.NewsDetailsUseCase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import com.example.theustimes.utils.Result
import com.example.theustimes.utils.errorProvider.ErrorProvider

class NewsDetailsViewModel(
    private val newsDetailsUseCase: NewsDetailsUseCase,
    private val errorProvider: ErrorProvider
) : ViewModel() {

    lateinit var article: Articles

    var likes: ObservableField<Int> = ObservableField(0)
    var comments: ObservableField<Int> = ObservableField(0)

    private val _requestLikesLiveData: MutableLiveData<Result<LikesAndCommentsApiResponse>> = MutableLiveData()
    val requestLikesLiveData: LiveData<Result<LikesAndCommentsApiResponse>>
        get() = _requestLikesLiveData

    private val _requestCommentsLiveData: MutableLiveData<Result<LikesAndCommentsApiResponse>> = MutableLiveData()
    val requestCommentsLiveData: LiveData<Result<LikesAndCommentsApiResponse>>
        get() = _requestCommentsLiveData

    fun getImageUrl(): String? {
        if (article.urlToImage != null && article.urlToImage.trim().isNotEmpty()) {
            return article.urlToImage
        }
        //returning null as picasso throws illegal path exception if imageUrl is empty
        return null
    }

    fun fetchData(articleId: String) {
        getLikes(articleId)
        getComments(articleId)
    }

    private fun getLikes(articleId: String) {
        viewModelScope.launch {
            try {
                _requestLikesLiveData.postValue(Result.Success(newsDetailsUseCase.getLikes(articleId)))
            } catch (exception: Exception) {
                _requestLikesLiveData.postValue(Result.Error(errorProvider.getErrorMessage(exception)))
            }
        }
    }

    private fun getComments(articleId: String) {
        viewModelScope.launch {
            try {
                _requestCommentsLiveData.postValue(Result.Success(newsDetailsUseCase.getComments(articleId)))
            } catch (exception: Exception) {
                _requestCommentsLiveData.postValue(Result.Error(errorProvider.getErrorMessage(exception)))
            }
        }
    }
}