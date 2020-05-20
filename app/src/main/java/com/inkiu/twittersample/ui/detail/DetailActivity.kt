package com.inkiu.twittersample.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.inkiu.twittersample.R
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.ui.base.BaseActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
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

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

}