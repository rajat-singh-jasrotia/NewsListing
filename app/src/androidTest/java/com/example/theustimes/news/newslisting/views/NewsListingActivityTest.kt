package com.example.theustimes.news.newslisting.views

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsListingActivityTest {
    var mContext: Context? = null

    @get:Rule
    var mRule: ActivityTestRule<NewsListingActivity> =
        ActivityTestRule(NewsListingActivity::class.java, false, false)

    @Before
    fun setUp() {
        mContext = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testListActivity_whenLaunched_And_VerifyIntent() {
        val intent = Intent(mContext, NewsListingActivity::class.java)
        val activity: NewsListingActivity =
            mRule.launchActivity(intent)
        assertNotNull(activity)
    }

}