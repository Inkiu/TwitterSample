package com.inkiu.twittersample.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.inkiu.twittersample.R
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.common.toVisibility
import com.inkiu.twittersample.ui.base.BaseActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.model.User
import com.inkiu.twittersample.ui.common.tweets.TweetAdapter
import com.inkiu.twittersample.ui.common.tweets.TweetClickListener
import com.inkiu.twittersample.ui.common.LoadingState
import com.inkiu.twittersample.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), TweetClickListener {

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    @Inject
    lateinit var vmFactory: HomeVMFactory
    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, vmFactory)[HomeViewModel::class.java]
    }

    private val adapter: TweetAdapter by lazy {
        TweetAdapter(imageLoader, this)
    }

    override fun getViewModel(): BaseViewModel = viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initTweetRecyclerView()
        initRefreshLayout()
        observe()
    }

    private fun initTweetRecyclerView() {
        tweetRecyclerView.adapter = adapter
        tweetRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL)
        )
    }

    private fun initRefreshLayout() {
        homeTweetRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
        homeFloatingButton.setOnClickListener {
            viewModel.refresh()
        }
    }

    private fun observe() {
        viewModel.pagingListData.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
            homeTweetRefreshLayout.isRefreshing = it is LoadingState.LoadingInitial
            homeFloatingButton.visibility = (it is LoadingState.Failure).toVisibility()
        })
        viewModel.userData.observe(this, Observer {
            toolbar.title = it.displayName
            toolbar.subtitle = it.name
            imageLoader.loadCircleProfile(it.profileUrl, toolbarImage)
        })
    }

    override fun onClickTweet(user: User) {
        startActivity(DetailActivity.newIntent(this, user.id))
    }
}