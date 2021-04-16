package com.example.theustimes.news.newsdetails.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.theustimes.R
import com.example.theustimes.common_ui.base.activity.BaseDataBindingActivity
import com.example.theustimes.databinding.NewsDetailsActivityDataBinding
import com.example.theustimes.di.DaggerProvider
import com.example.theustimes.news.models.Articles
import com.example.theustimes.news.newsdetails.viewmodel.NewsDetailsViewModel
import com.example.theustimes.news.newsdetails.viewmodel.NewsDetailsViewModelFactory
import com.example.theustimes.utils.Result
import javax.inject.Inject

class NewsDetailsActivity :
    BaseDataBindingActivity<NewsDetailsActivityDataBinding>(
        R.layout.activity_news_details
    ) {
    private var TAG: String = NewsDetailsActivity::class.java.name

    @Inject
    lateinit var viewModelFactory: NewsDetailsViewModelFactory

    private val newsDetailsViewModel by viewModels<NewsDetailsViewModel>(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsDetailsViewModel.requestLikesLiveData.observe(this, Observer {
            when(it) {
                is Result.Success -> {
                    newsDetailsViewModel.likes.set(it.data.likes)
                    Toast.makeText(this, "Count:: "+it.data.likes, Toast.LENGTH_LONG).show()
                }
                is Result.Error -> {
                    Toast.makeText(this,  it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        })

        newsDetailsViewModel.requestCommentsLiveData.observe(this, Observer {
            when(it) {
                is Result.Success -> {
                    newsDetailsViewModel.comments.set(it.data.comments)
                    Toast.makeText(this, "Count:: "+it.data.likes, Toast.LENGTH_LONG).show()
                }
                is Result.Error -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
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
    }

    private fun getIntentData() {
        intent?.extras?.let {
            if (it["data"] != null) {
                newsDetailsViewModel.article = it.getParcelable<Articles>("data") as Articles
            }
        }

    }

    private fun updateArticleData() {
        supportActionBar?.title = "Details"
        val articleID = getArticleID(newsDetailsViewModel.article.url)
        newsDetailsViewModel.fetchData(articleID)
    }

    fun getArticleID(url: String): String {
        return url.replace("/", "-")
    }
}