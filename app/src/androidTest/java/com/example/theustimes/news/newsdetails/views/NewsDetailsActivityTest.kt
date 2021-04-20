package com.example.theustimes.news.newsdetails.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.theustimes.news.models.Articles
import com.example.theustimes.news.newslisting.views.NewsListingActivity
import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsDetailsActivityTest {
    var mContext: Context? = null

    @get:Rule
    var mRule: ActivityTestRule<NewsDetailsActivity> =
        ActivityTestRule(NewsDetailsActivity::class.java, false, false)

    @Before
    fun setUp() {
        mContext = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testDetailsActivity_whenLaunched_And_VerifyIntent() {
        val bundle = Bundle()
        bundle.putParcelable("data", getArticle())
        val intent = Intent(mContext, NewsDetailsActivity::class.java)
        intent.putExtras(bundle)
        val activity: NewsDetailsActivity =
            mRule.launchActivity(intent)
        Thread.sleep(10000)
        assertNotNull(activity)
    }

    private fun getArticle(): Articles {
        return Articles(author = "Variety",
            title = "BBC sets up complaints line for 'too much TV coverage' of Prince Philip's death - NBC News",
            description = "The BBC has set up a complaints line after getting complaints of too much television coverage of the death of Prince Philip, Duke of Edinburgh. He died at the age of 99.",
            url = "https://www.nbcnews.com/news/world/bbc-sets-complaints-line-too-much-tv-coverage-prince-philip-n1263711",
            urlToImage = "https://media2.s-nbcnews.com/j/newscms/2021_06/3449445/210211-bbc-hq-london-jm-1354_ad8cb95e88b0ea311ec57600110f9f6f.nbcnews-fp-1200-630.jpg",
            publishedAt = "2021-04-10T03:31:00Z",
            content = "LOS ANGELES The BBC has set up a dedicated complaints page for viewers fed up with its blanket coverage of the death of Prince Philip.")
    }

}