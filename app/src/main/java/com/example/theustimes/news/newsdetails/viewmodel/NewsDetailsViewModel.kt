package com.example.theustimes.news.newsdetails.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theustimes.news.models.Articles
import com.example.theustimes.news.models.LikesAndCommentsApiResponse
import com.example.theustimes.news.newsdetails.usecase.NewsDetailsUseCase
import com.example.theustimes.utils.Result
import kotlinx.coroutines.launch
import com.example.theustimes.errorProvider.ErrorProvider

class NewsDetailsViewModel(
    private val newsDetailsUseCase: NewsDetailsUseCase,
    private val errorProvider: ErrorProvider
) : ViewModel() {

    lateinit var article: Articles
    var likesText: ObservableField<String> = ObservableField("Like")
    var commentTxt: ObservableField<String> = ObservableField("Comment")

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

    fun updateCommentData(comments: Int?) {
        comments?.let {
            when {
                it > 1 -> {
                    commentTxt.set("$it Comments")
                }
                it == 1 -> {
                    commentTxt.set("$it Comment")
                }
                else -> {
                    commentTxt.set("Comment")
                }
            }
        }
    }

    fun updateLikesData(likes: Int?) {
        likes?.let {
            when {
                it > 1 -> {
                    likesText.set("$it Likes")
                }
                it == 1 -> {
                    likesText.set("$it Like")
                }
                else -> {
                    likesText.set("Like")
                }
            }
        }
    }

    fun fetchData(articleId: String) {
        getLikes(articleId)
        getComments(articleId)
    }

    fun getLikes(articleId: String) {
        viewModelScope.launch {
            try {
                _requestLikesLiveData.postValue(Result.success(newsDetailsUseCase.getLikes(articleId)))
            } catch (exception: Exception) {
                _requestLikesLiveData.postValue(Result.error(errorProvider.getErrorMessage(exception)))
            }
        }
    }

    fun getComments(articleId: String) {
        viewModelScope.launch {
            try {
                _requestCommentsLiveData.postValue(Result.success(newsDetailsUseCase.getComments(articleId)))
            } catch (exception: Exception) {
                _requestCommentsLiveData.postValue(Result.error(errorProvider.getErrorMessage(exception)))
            }
        }
    }
}