package com.inkiu.twittersample.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inkiu.domain.usecase.GetReplyTweets
import com.inkiu.domain.usecase.GetTweet
import com.inkiu.twittersample.di.PerActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.model.Tweet
import com.inkiu.twittersample.ui.common.model.mapper.TweetEntityTweetMapper
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class DetailViewModel(
    private val tweetId: Long,
    private val getTweet: GetTweet,
    private val getReplyTweets: GetReplyTweets,
    private val tweetMapper: TweetEntityTweetMapper
) : BaseViewModel() {

    val detailData = MutableLiveData<Tweet>()

    override fun onAttached() {
        launch { getTweetDetail() }
    }

    private fun refresh() {

    }
    
    private fun getTweetDetail() {
        launch {
            val detail = getTweet.execute(GetTweet.Param(tweetId)).let { 
                tweetMapper(it)
            }
        }
    }
}

@PerActivity
class DetailVmFactory @Inject constructor(
    @Named("tweet_id") private val tweetId: Long,
    private val getTweet: GetTweet,
    private val getReplyTweets: GetReplyTweets,
    private val tweetMapper: TweetEntityTweetMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(
            tweetId,
            getTweet,
            getReplyTweets,
            tweetMapper
        ) as T
    }
}