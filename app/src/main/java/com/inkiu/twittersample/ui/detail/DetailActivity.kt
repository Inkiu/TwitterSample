package com.inkiu.twittersample.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.inkiu.twittersample.R
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.ui.base.BaseActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.tweets.TweetAdapter
import com.inkiu.twittersample.ui.common.tweets.TweetClickListener
import com.inkiu.twittersample.ui.common.tweets.datasource.DataSourceState
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    companion object {
        const val ARG_TWEET_ID = "ARG_TWEET_ID"
        fun newIntent(context: Context, tweetId: Long) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(ARG_TWEET_ID, tweetId)
            }
    }

    @Inject lateinit var vmFactory: DetailVmFactory
    @Inject lateinit var imageLoader: ImageLoader

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, vmFactory)[DetailViewModel::class.java]
    }

    private val adapter: TweetAdapter by lazy {
        TweetAdapter(imageLoader, object : TweetClickListener {
            override fun onClickTweet(tweetId: Long) {
                /* no-op */
            }

            override fun onClickUser(userId: Long) {
                /* no-op */
            }
        })
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initTweetRecyclerView()
        observe()
    }

    private fun initTweetRecyclerView() {
        detailReplyRecyclerView.adapter = adapter
        detailReplyRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL)
        )
    }

    private fun observe() {
        viewModel.pagingListData.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.networkStateData.observe(this, Observer {
            adapter.setNetworkState(it)
//            homeTweetRefreshLayout.isRefreshing = it is DataSourceState.LoadingInitial
        })
        viewModel.detailData.observe(this, Observer {
            detailText.text = it.user.name
        })
    }

}