package com.example.theustimes.news.newsdetails.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.theustimes.R
import com.example.theustimes.common_ui.base.activity.BaseDataBindingActivity
import com.example.theustimes.databinding.NewsDetailsActivityDataBinding
import com.example.theustimes.di.DaggerProvider
import com.example.theustimes.news.models.Articles
import com.example.theustimes.news.newsdetails.viewmodel.NewsDetailsViewModel
import com.example.theustimes.news.newsdetails.viewmodel.NewsDetailsViewModelFactory
import com.example.theustimes.utils.Status
import com.example.theustimes.utils.Util
import javax.inject.Inject

/**
 * A simple [BaseDataBindingActivity] subclass to show
 * details of the selected News/Articles
 *
 * Use the [NewsDetailsActivity.getIntent] factory method to
 * get the intent to launch this activity
 *
 */
class NewsDetailsActivity :
    BaseDataBindingActivity<NewsDetailsActivityDataBinding>(
        R.layout.activity_news_details
    ) {

    companion object {
        /**
         * Use this factory method to create a new instance of
         * [NewsDetailsActivity] using the provided parameters.
         *
         * param [context] of type [Context] of caller
         *
         *  param [Articles] object
         *
         * return [Intent] to launch this activity
         *
         */
        fun getIntent(context: Context, article: Articles): Intent {
            val bundle = Bundle()
            bundle.putParcelable("data", article)
            return Intent(context, NewsDetailsActivity::class.java).apply {
                putExtras(bundle)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: NewsDetailsViewModelFactory

    @Inject
    lateinit var util: Util

    private val newsDetailsViewModel by viewModels<NewsDetailsViewModel>(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsDetailsViewModel.requestLikesLiveData.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    newsDetailsViewModel.updateLikesData(it.data?.likes)
                }
                Status.ERROR -> {
                    //do nothing
                }
            }
        })

        newsDetailsViewModel.requestCommentsLiveData.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    newsDetailsViewModel.updateCommentData(it.data?.comments)
                }
                Status.ERROR -> {
                    //do nothing
                }
            }
        })

    }

    override fun injectDaggerComponent() {
        DaggerProvider.getAppComponent()?.inject(this)
    }

    override fun onDataBindingCreated() {
        getIntentData()
        updateArticleData()
        binding.viewModel = newsDetailsViewModel
        binding.materialToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun getIntentData() {
        intent?.extras?.let {
            if (it["data"] != null) {
                newsDetailsViewModel.article = it.getParcelable<Articles>("data") as Articles
            }
        }

    }

    private fun updateArticleData() {
//        supportActionBar?.title = "Details"
        val articleID = util.getArticleID(newsDetailsViewModel.article.url)
        newsDetailsViewModel.fetchData(articleID)
    }
}