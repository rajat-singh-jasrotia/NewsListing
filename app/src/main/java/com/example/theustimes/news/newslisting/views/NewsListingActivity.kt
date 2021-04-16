package com.example.theustimes.news.newslisting.views

import android.os.Bundle
import com.example.theustimes.R
import com.example.theustimes.common_ui.base.activity.BaseActivity
import androidx.fragment.app.commit

class NewsListingActivity : BaseActivity(R.layout.activity_news_listing) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "News List"
        supportFragmentManager.commit {
            add(
                R.id.news_listing_container,
                NewsListingFragment.newInstance()
            )
        }

    }

    override fun injectDaggerComponent() {
        //Do nothing as we aren't injecting anything
    }
}