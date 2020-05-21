package com.inkiu.twittersample.ui.home.homeprofile

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.inkiu.domain.usecase.GetUserTweets
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.model.Tweet
import com.inkiu.twittersample.model.mapper.TweetEntityTweetMapper
import com.inkiu.twittersample.ui.common.tweets.datasource.UserTweetDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

class ProfileViewModel(
    private val tweetMapper: TweetEntityTweetMapper,
    private val getUserTweets: GetUserTweets
) : BaseViewModel() {

    private val dataSourceData = MutableLiveData<UserTweetDataSource>()

    // exposed live data
    val pagingListData = dataSourceData.map { createPagedList(it) }
    val networkStateData = dataSourceData.switchMap { it.state }

    override fun onAttached() {
        refresh()
    }

    fun refresh() {
            dataSourceData.value = createDataSource()
    }

    private fun createDataSource(): UserTweetDataSource {
        return UserTweetDataSource(getUserTweets, viewModelScope, tweetMapper)
    }

    private fun createPagedList(dataSource: UserTweetDataSource): PagedList<Tweet> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(100)
            .setPrefetchDistance(10)
            .build()
        return PagedList.Builder(dataSource, config)
            .setFetchExecutor(Dispatchers.Main.asExecutor())
            .setNotifyExecutor(Dispatchers.Main.asExecutor())
            .build()
    }
}

//@PerFragment
class ProfileVMFactory constructor(
    private val tweetMapper: TweetEntityTweetMapper,
    private val getUserTweets: GetUserTweets
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            tweetMapper,
            getUserTweets
        ) as T
    }
}