package com.example.theustimes.news.newsdetails.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.theustimes.R
import com.example.theustimes.TestCaseUtils
import com.example.theustimes.news.models.LikesAndCommentsApiResponse
import com.example.theustimes.news.newsdetails.usecase.NewsDetailsUseCase
import com.example.theustimes.utils.Result
import com.example.theustimes.utils.Util
import com.example.theustimes.errorProvider.ErrorProvider
import com.example.theustimes.errorProvider.ErrorProviderImpl
import com.nhaarman.mockitokotlin2.atLeast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NewsDetailsViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCaseUtils.TestCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var errorProvider: ErrorProvider

    @Mock
    private lateinit var newsDetailsUseCase: NewsDetailsUseCase

    @Mock
    private lateinit var apiLikesObserver: Observer<Result<LikesAndCommentsApiResponse>>

    @Mock
    private lateinit var apiCommentsObserver: Observer<Result<LikesAndCommentsApiResponse>>

    private lateinit var util: Util

    private val context: Context? = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        errorProvider = ErrorProviderImpl()
        util = Util()
    }

    @Test
    fun `given success state, when getLikes called, then update live data for success status`() {
        testCoroutineRule.runBlockingTest {
            val mockResponse = getMockResponse(R.raw.like_api_reponse)
            Mockito.doReturn(mockResponse)
                .`when`(newsDetailsUseCase)
                .getLikes("dummyId")

            val viewModel = NewsDetailsViewModel(newsDetailsUseCase, errorProvider)

            viewModel.requestLikesLiveData.observeForever(apiLikesObserver)

            viewModel.getLikes("dummyId")

            Mockito.verify(apiLikesObserver, atLeast(1)).onChanged(Result.success(mockResponse))
            Mockito.verify(newsDetailsUseCase).getLikes("dummyId")
        }
    }

    @Test
    fun `given success state, when getComments called, then update live data for success status`() {
        testCoroutineRule.runBlockingTest {
            val mockResponse = getMockResponse(R.raw.commnet_api_reponse)
            Mockito.doReturn(mockResponse)
                .`when`(newsDetailsUseCase)
                .getComments("dummyId")

            val viewModel = NewsDetailsViewModel(newsDetailsUseCase, errorProvider)

            viewModel.requestCommentsLiveData.observeForever(apiCommentsObserver)

            viewModel.getComments("dummyId")

            Mockito.verify(apiCommentsObserver, atLeast(1)).onChanged(Result.success(mockResponse))
            Mockito.verify(newsDetailsUseCase).getComments("dummyId")
        }
    }

    private fun getMockResponse(resourceId: Int): LikesAndCommentsApiResponse {
        val jsonString: String = util.getStringFromRawResource(context, resourceId)
        return util.convertJsonToObject(
            jsonString,
            LikesAndCommentsApiResponse::class.java
        )
    }
}