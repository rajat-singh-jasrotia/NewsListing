package com.example.theustimes.news.newslisting.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.theustimes.R
import com.example.theustimes.common_ui.base.fragment.BaseDataBindingFragment
import com.example.theustimes.databinding.NewsListingFragmentDataBinding
import com.example.theustimes.di.DaggerProvider
import com.example.theustimes.news.models.Articles
import com.example.theustimes.news.newsdetails.views.NewsDetailsActivity
import com.example.theustimes.news.newslisting.viewmodel.NewsListingViewModel
import com.example.theustimes.news.newslisting.viewmodel.NewsListingViewModelFactory
import com.example.theustimes.news.newslisting.views.adapter.NewsItemClickListener
import com.example.theustimes.news.newslisting.views.adapter.NewsListAdapter
import com.example.theustimes.utils.Constants
import com.example.theustimes.utils.Status
import javax.inject.Inject

/**
 * A simple [Fragment] subclass to show News Listing.
 *
 * Use the [NewsListingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsListingFragment:
    BaseDataBindingFragment<NewsListingFragmentDataBinding>(
        R.layout.fragment_news_listing
    ), NewsItemClickListener {

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * return A new instance of fragment [NewsListingFragment].
         */
        fun newInstance() =
            NewsListingFragment()
    }

    @Inject
    lateinit var viewModelFactory: NewsListingViewModelFactory

    private var newsListAdapter: NewsListAdapter? = null

    private val newsListingViewModel by viewModels<NewsListingViewModel>(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAdapter()
        setupObserver()

    }

    override fun injectDaggerComponent() {
        DaggerProvider.getAppComponent()?.inject(this)
    }

    override fun onDataBindingCreated() {
        binding.viewModel = newsListingViewModel
        binding.listAdapter = newsListAdapter
        // Calling the fetchNewsData api
        newsListingViewModel.fetchNewsData(Constants.country_code, Constants.api_key)
        binding.swipeToRefresh.setOnRefreshListener {
            newsListingViewModel.swipeLoadingVisibility.set(true)
            newsListingViewModel.fetchNewsData(Constants.country_code, Constants.api_key)
        }
    }

    private fun setupObserver() {
        newsListingViewModel.resultNewsList.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    newsListingViewModel.errorVisibility.set(false)
                    newsListingViewModel.progressBarState.set(true)
                    newsListingViewModel.recyclerViewState.set(false)
                }
                Status.ERROR -> {
                    newsListingViewModel.swipeLoadingVisibility.set(false)
                    newsListingViewModel.progressBarState.set(false)
                    newsListingViewModel.recyclerViewState.set(false)
                    newsListingViewModel.errorMsg.set(it.message)
                    newsListingViewModel.errorVisibility.set(true)
                }
                Status.SUCCESS -> {
                    newsListingViewModel.swipeLoadingVisibility.set(false)
                    newsListingViewModel.progressBarState.set(false)
                    newsListingViewModel.recyclerViewState.set(true)
                    it.data?.let { it1 -> setAdapter(it1) }
                    newsListingViewModel.errorVisibility.set(false)
                }
            }
        })
    }

    private fun setAdapter(data: List<Articles> = emptyList()) {
        if (newsListAdapter == null) {
            newsListAdapter = NewsListAdapter(newsItemClickListener = this)
        }
        newsListAdapter?.updateAll(data.toMutableList())
    }

    override fun onNewsItemClicked(article: Articles) {
        startActivity(
            NewsDetailsActivity.getIntent(requireActivity(), article)
        )
    }

}