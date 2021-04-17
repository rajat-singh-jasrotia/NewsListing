package com.example.theustimes.news.newsdetails.usecase

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.theustimes.R
import com.example.theustimes.TestCaseUtils
import com.example.theustimes.news.models.LikesAndCommentsApiResponse
import com.example.theustimes.news.newsdetails.remote.NewsDetailsApiService
import com.example.theustimes.news.newsdetails.repository.NewsDetailsRepositoryImpl
import com.example.theustimes.utils.Util
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NewsDetailsUseCaseImplTest {
    private lateinit var newsDetailsUseCase: NewsDetailsUseCase
    private lateinit var newsDetailsApiService: NewsDetailsApiService
    private lateinit var util: Util
    private val context: Context? = ApplicationProvider.getApplicationContext()


    @get:Rule
    val testCoroutineRule = TestCaseUtils.TestCoroutineRule()

    @Before
    fun setUp() {
        newsDetailsApiService = mock()
        util = Util()
        newsDetailsUseCase = NewsDetailsUseCaseImpl(NewsDetailsRepositoryImpl(newsDetailsApiService))
    }

    @Test
    fun `test getLikes api return response`() {
        testCoroutineRule.runBlockingTest {
            // Given
            val jsonString: String = util.getStringFromRawResource(context, R.raw.like_api_reponse)
            whenever(newsDetailsApiService.getLikes(any())).thenReturn(
                util.convertJsonToObject(
                    jsonString,
                    LikesAndCommentsApiResponse::class.java
                )
            )
            //when
            val articleID =
                util.getArticleID("https://www.nbcnews.com/news/world/bbc-sets-complaints-line-too-much-tv-coverage-prince-philip-n1263711")
            newsDetailsUseCase.getLikes(articleID)

            //then
            verify(newsDetailsApiService, atLeastOnce()).getLikes(articleID)
        }
    }

    @Test
    fun `test getComments api return response`() {
        testCoroutineRule.runBlockingTest {
            // Given
            val jsonString: String =
                util.getStringFromRawResource(context, R.raw.commnet_api_reponse)
            whenever(newsDetailsApiService.getComments(any())).thenReturn(
                util.convertJsonToObject(
                    jsonString,
                    LikesAndCommentsApiResponse::class.java
                )
            )
            //when
            val articleID =
                util.getArticleID("https://www.nbcnews.com/news/world/bbc-sets-complaints-line-too-much-tv-coverage-prince-philip-n1263711")
            newsDetailsUseCase.getComments(articleID)

            //then
            verify(newsDetailsApiService, atLeastOnce()).getComments(articleID)
        }
    }

}