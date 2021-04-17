package com.example.theustimes.news.models

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.theustimes.R
import com.example.theustimes.utils.Util
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
class NewsListingApiResponseTest {
    lateinit var util: Util
    private val context: Context? = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        util = Util()
    }

    @Test
    fun `test deserialization news listing api response`() {
        val jsonString: String = util.getStringFromRawResource(context, R.raw.list_api_response)
        assertNotNull(jsonString)
        val newsListingApiResponse: NewsListingApiResponse =
            util.convertJsonToObject(jsonString, NewsListingApiResponse::class.java)
        assertNotNull(newsListingApiResponse)
        assertEquals(1, newsListingApiResponse.totalResults)
        assertEquals(1, newsListingApiResponse.articles.size)

        val articles = newsListingApiResponse.articles[0];
        assertEquals("Variety", articles.author)
        assertEquals(
            "BBC sets up complaints line for 'too much TV coverage' of Prince Philip's death - NBC News",
            articles.title
        )
        assertEquals(
            "The BBC has set up a complaints line after getting complaints of too much television coverage of the death of Prince Philip, Duke of Edinburgh. He died at the age of 99.",
            articles.description
        )
        assertEquals(
            "https://www.nbcnews.com/news/world/bbc-sets-complaints-line-too-much-tv-coverage-prince-philip-n1263711",
            articles.url
        )
        assertEquals(
            "https://media2.s-nbcnews.com/j/newscms/2021_06/3449445/210211-bbc-hq-london-jm-1354_ad8cb95e88b0ea311ec57600110f9f6f.nbcnews-fp-1200-630.jpg",
            articles.urlToImage
        )
        assertEquals("2021-04-10T03:31:00Z", articles.publishedAt)
        assertEquals(
            "LOS ANGELES The BBC has set up a dedicated complaints page for viewers fed up with its blanket coverage of the death of Prince Philip.",
            articles.content
        )

    }
}