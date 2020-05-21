package com.inkiu.twittersample.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.appbar.AppBarLayout
import com.inkiu.twittersample.R
import com.inkiu.twittersample.common.getDecimalSize
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.common.relatedTimeString
import com.inkiu.twittersample.ui.base.BaseActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.model.User
import com.inkiu.twittersample.ui.common.model.UserDetail
import com.inkiu.twittersample.ui.common.tweets.TweetAdapter
import com.inkiu.twittersample.ui.common.tweets.TweetClickListener
import com.inkiu.twittersample.ui.common.tweets.datasource.DataSourceState
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_profile.*
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    companion object {
        const val ARG_USER_ID = "ARG_USER_ID"
        fun newIntent(context: Context, userId: Long) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(ARG_USER_ID, userId)
            }
    }

    @Inject lateinit var vmFactory: DetailVmFactory
    @Inject lateinit var imageLoader: ImageLoader

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, vmFactory)[DetailViewModel::class.java]
    }

    private val adapter: TweetAdapter by lazy {
        TweetAdapter(imageLoader, object : TweetClickListener {
            override fun onClickTweet(user: User) {
                /* no-op */
            }
        })
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initTweetRecyclerView()
        initRefreshLayout()
        observe()
    }

    private fun initTweetRecyclerView() {
        detailReplyRecyclerView.adapter = adapter
        detailReplyRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL)
        )

        detailAppbarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var visible = true
            var scrollRange = -1;
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    detailCollapsingToolbarLayout.isTitleEnabled = true// = View.VISIBLE
                    visible = true
                } else if (visible) {
                    detailCollapsingToolbarLayout.isTitleEnabled = false // = View.INVISIBLE
                    visible = false
                }
            }
        })
    }

    private fun initRefreshLayout() {
        userRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun bindUserProfile(user: UserDetail) {
        imageLoader.loadCircleProfile(user.profileUrl, profileImageView)
        profileDisplayName.text = user.displayName
        profileName.text = user.name
        profileDescription.text = user.description

        profilePlace.text = user.location

        profileCreateDate.text = user.joinedDate.relatedTimeString()

        profileFollowingCount.text = user.followingCount.getDecimalSize()
        profileFollowerCount.text = user.followerCount.getDecimalSize()

    }

    private fun observe() {
        viewModel.pagingListData.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.networkStateData.observe(this, Observer {
            adapter.setNetworkState(it)
            userRefreshLayout.isRefreshing = it is DataSourceState.LoadingInitial
        })
        viewModel.detailData.observe(this, Observer {
            detailCollapsingToolbarLayout.title = "false"
            bindUserProfile(it)
        })
    }

}