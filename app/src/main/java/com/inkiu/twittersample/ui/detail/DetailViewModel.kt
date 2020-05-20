package com.inkiu.twittersample.ui.detail

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.inkiu.domain.usecase.GetReplyTweets
import com.inkiu.domain.usecase.GetTweet
import com.inkiu.twittersample.di.PerActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.model.Tweet
import com.inkiu.twittersample.ui.common.model.mapper.TweetEntityTweetMapper
import com.inkiu.twittersample.ui.common.tweets.datasource.HomeTweetDataSource
import com.inkiu.twittersample.ui.common.tweets.datasource.ReplyTweetDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class DetailViewModel(
    private val tweetId: Long,
    private val getTweet: GetTweet,
    private val getReplyTweets: GetReplyTweets,
    private val tweetMapper: TweetEntityTweetMapper
) : BaseViewModel() {

    private val _detailData = MutableLiveData<Tweet>()
    private val dataSourceData = _detailData.map { createDataSource() }

    val detailData = _detailData.map { it }
    val pagingListData = dataSourceData.map { createPagedList(it) }
    val networkStateData = dataSourceData.switchMap { it.state }

    override fun onAttached() {
        launch { getTweetDetail() }
    }
    
    private fun getTweetDetail() {
        launch {
            getTweet.execute(GetTweet.Param(tweetId)).let {
                tweetMapper(it)
            }.let {
                _detailData.value = it
            }
        }
    }

    private fun createDataSource(): ReplyTweetDataSource {
        val userName = detailData.value?.user?.name ?: ""
        return ReplyTweetDataSource(tweetId, userName, getReplyTweets, viewModelScope, tweetMapper)
    }

    private fun createPagedList(dataSource: ReplyTweetDataSource): PagedList<Tweet> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setPrefetchDistance(3)
            .build()
        return PagedList.Builder(dataSource, config)
            .setFetchExecutor(Dispatchers.Main.asExecutor())
            .setNotifyExecutor(Dispatchers.Main.asExecutor())
            .build()
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