package com.inkiu.twittersample.ui.home.homeprofile

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.domain.usecase.GetUserTweets
import com.inkiu.twittersample.di.PerFragment
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.model.Tweet
import com.inkiu.twittersample.ui.common.model.mapper.TweetEntityTweetMapper
import com.inkiu.twittersample.ui.common.tweets.datasource.HomeTweetDataSource
import com.inkiu.twittersample.ui.common.tweets.datasource.UserTweetDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

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

@PerFragment
class ProfileVMFactory @Inject constructor(
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