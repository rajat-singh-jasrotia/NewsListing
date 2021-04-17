package com.example.theustimes.news.newslisting.views

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.theustimes.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsListingFragmentTest {
    var mContext: Context? = null

    @get:Rule
    var mRule: ActivityTestRule<NewsListingActivity> =
        ActivityTestRule(NewsListingActivity::class.java, false, false)

    @Before
    fun setUp() {
        mContext = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testNewsListingFragment_whenLaunched_And_DisplayTheListData() {
        val intent = Intent(mContext, NewsListingActivity::class.java)
        mRule.launchActivity(intent)

       onView(withId(R.id.progressBar))
            .check(matches(isDisplayed()))

        // Adding the 5 sec sleep here until the api response load
        Thread.sleep(10000)
        onView(withId(R.id.rv_list_articles)).check(matches(isDisplayed()))
    }
}