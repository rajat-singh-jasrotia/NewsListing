package com.example.theustimes.di.component

import com.example.theustimes.app.MyApplication
import com.example.theustimes.di.modules.ApplicationModule
import com.example.theustimes.di.modules.NetworkModule
import com.example.theustimes.news.newsdetails.views.NewsDetailsActivity
import com.example.theustimes.news.newslisting.views.NewsListingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class
])
interface AppComponent {

    fun inject(myApplication: MyApplication)

    fun inject(newsListingFragment: NewsListingFragment)

    fun inject(newsDetailsActivity: NewsDetailsActivity)

}