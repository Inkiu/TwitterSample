package com.inkiu.twittersample.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inkiu.domain.usecase.GetReplyTweets
import com.inkiu.domain.usecase.GetTweet
import com.inkiu.twittersample.di.PerActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.model.mapper.TweetEntityTweetMapper
import javax.inject.Inject
import javax.inject.Named

class DetailViewModel(
    private val tweetId: Long,
    private val getTweet: GetTweet,
    private val getReplyTweets: GetReplyTweets,
    private val tweetMapper: TweetEntityTweetMapper
) : BaseViewModel() {

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