package com.example.theustimes.news.newslisting.usecase

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.theustimes.R
import com.example.theustimes.TestCaseUtils
import com.example.theustimes.news.models.NewsListingApiResponse
import com.example.theustimes.news.newslisting.remote.NewsListingApiService
import com.example.theustimes.news.newslisting.repository.NewsListingRepositoryImpl
import com.example.theustimes.utils.Constants
import com.example.theustimes.utils.Util
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NewsListingUseCaseImplTest {
    private lateinit var newsListingUseCase: NewsListingUseCase
    private lateinit var newsListingApiService: NewsListingApiService
    private lateinit var util: Util
    private val context: Context? = ApplicationProvider.getApplicationContext()

    @get:Rule
    val testCoroutineRule = TestCaseUtils.TestCoroutineRule()

    @Before
    fun setUp() {
        newsListingApiService = mock()
        util = Util()
        newsListingUseCase = NewsListingUseCaseImpl(NewsListingRepositoryImpl(newsListingApiService))
    }

    @Test
    fun `test getTopNews api return response`() {
        testCoroutineRule.runBlockingTest {
            // Given
            val jsonString: String = util.getStringFromRawResource(context, R.raw.list_api_response)
            whenever(newsListingApiService.getTopNews(any(), any())).thenReturn(
                util.convertJsonToObject(
                    jsonString,
                    NewsListingApiResponse::class.java
                )
            )
            //when
            newsListingUseCase.getTopNews(
                country = Constants.country_code,
                apiKey = Constants.api_key
            )
            //then
            verify(newsListingApiService, atLeastOnce()).getTopNews(
                country = Constants.country_code,
                apiKey = Constants.api_key
            )
        }
    }

}