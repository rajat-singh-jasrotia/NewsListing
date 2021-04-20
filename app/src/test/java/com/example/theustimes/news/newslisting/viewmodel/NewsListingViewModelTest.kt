package com.example.theustimes.news.newslisting.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.theustimes.R
import com.example.theustimes.TestCaseUtils
import com.example.theustimes.news.models.Articles
import com.example.theustimes.news.models.NewsListingApiResponse
import com.example.theustimes.news.newslisting.usecase.NewsListingUseCase
import com.example.theustimes.utils.*
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
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NewsListingViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCaseUtils.TestCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var errorProvider: ErrorProvider

    @Mock
    private lateinit var newsListingUseCase: NewsListingUseCase

    @Mock
    private lateinit var apiNewsObserver: Observer<Result<List<Articles>>>

    private lateinit var util: Util

    private val context: Context? = ApplicationProvider.getApplicationContext()

    private lateinit var viewModel: NewsListingViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        errorProvider = ErrorProviderImpl()
        viewModel = NewsListingViewModel(newsListingUseCase, errorProvider)
        util = Util()
    }


    @Test
    fun `given success state, when getTopNews called, then update live data for success status`() {
        testCoroutineRule.runBlockingTest {
            val mockResponse = getMockResponse()
            doReturn(mockResponse)
                .`when`(newsListingUseCase)
                .getTopNews(Constants.country_code, Constants.api_key)

            viewModel.resultNewsList.observeForever(apiNewsObserver)

            viewModel.fetchNewsData(Constants.country_code, Constants.api_key)

            verify(apiNewsObserver).onChanged(Result.loading())
            verify(apiNewsObserver, atLeast(1)).onChanged(Result.success(mockResponse.articles))
            verify(newsListingUseCase).getTopNews(Constants.country_code, Constants.api_key)
        }
    }

    @Test
    fun `given error state, when getTopNews called, then update live data for error status`() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = ""
            val exception = UnknownHostException(errorMessage)
            doReturn(exception)
                .`when`(newsListingUseCase)
                .getTopNews(Constants.country_code, Constants.api_key)

            viewModel.resultNewsList.observeForever(apiNewsObserver)


            viewModel.fetchNewsData(Constants.country_code, Constants.api_key)

            verify(apiNewsObserver).onChanged(Result.loading())

            verify(apiNewsObserver, atLeast(1)).onChanged(
                Result.error(errorMessage)
            )

            verify(newsListingUseCase).getTopNews(Constants.country_code, Constants.api_key)
        }
    }


    private fun getMockResponse(): NewsListingApiResponse {
        val jsonString: String = util.getStringFromRawResource(context, R.raw.list_api_response)
        return util.convertJsonToObject(
            jsonString,
            NewsListingApiResponse::class.java
        )
    }

}