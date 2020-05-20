package com.inkiu.twittersample.ui.home

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.twittersample.di.PerActivity
import com.inkiu.twittersample.di.PerFragment
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.model.Tweet
import com.inkiu.twittersample.ui.common.model.mapper.TweetEntityTweetMapper
import com.inkiu.twittersample.ui.common.tweets.datasource.HomeTweetDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Inject

class HomeViewModel(
    private val tweetMapper: TweetEntityTweetMapper,
    private val getHomeTweets: GetHomeTweets
) : BaseViewModel() {

    private val dataSourceData = MutableLiveData<HomeTweetDataSource>()

    // exposed live data
    val pagingListData = dataSourceData.map { createPagedList(it) }
    val networkStateData = dataSourceData.switchMap { it.state }

    override fun onAttached() {
        refresh()
    }

    fun refresh() {
        dataSourceData.value = createDataSource()
    }

    private fun createDataSource(): HomeTweetDataSource {
        return HomeTweetDataSource(getHomeTweets, viewModelScope, tweetMapper)
    }

    private fun createPagedList(dataSource: HomeTweetDataSource): PagedList<Tweet> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(100)
            .setPageSize(150)
            .setPrefetchDistance(10)
            .build()
        return PagedList.Builder(dataSource, config)
            .setFetchExecutor(Dispatchers.Main.asExecutor())
            .setNotifyExecutor(Dispatchers.Main.asExecutor())
            .build()
    }

}

@PerActivity
class HomeVMFactory @Inject constructor(
    private val tweetMapper: TweetEntityTweetMapper,
    private val getHomeTweets: GetHomeTweets
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
            tweetMapper,
            getHomeTweets
        ) as T
    }
}