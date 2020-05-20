package com.inkiu.twittersample.ui.home.hometweet

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.domain.usecase.GetUserDetail
import com.inkiu.twittersample.TwitterApp
import com.inkiu.twittersample.di.PerFragment
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.model.Tweet
import com.inkiu.twittersample.ui.common.model.mapper.TweetEntityTweetMapper
import com.inkiu.twittersample.ui.common.tweets.datasource.HomeTweetDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

class HomeTweetsViewModel(
    private val tweetMapper: TweetEntityTweetMapper,
    private val getHomeTweets: GetHomeTweets
) : BaseViewModel() {

    private val viewState = HomeTweetViewState()

    private val _dataSourceData = MutableLiveData<HomeTweetDataSource>()

    // exposed live data
    val pagingListData: LiveData<PagedList<Tweet>> = Transformations.map(_dataSourceData) {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setPrefetchDistance(3)
            .build()
        PagedList.Builder(it, config)
            .setFetchExecutor(Dispatchers.Main.asExecutor())
            .setNotifyExecutor(Dispatchers.Main.asExecutor())
            .build()
    }
    val viewStateData: MutableLiveData<HomeTweetViewState>
            = MutableLiveData(viewState)

    override fun onAttached() {
        _dataSourceData.value = createDataSource()
    }

    private fun createDataSource(): HomeTweetDataSource {
        return HomeTweetDataSource(getHomeTweets, viewModelScope, tweetMapper)
    }

    private fun HomeTweetViewState.post() {
        viewStateData.value = this
    }

}

@PerFragment
class HomeTweetsVMFactory @Inject constructor(
    private val tweetMapper: TweetEntityTweetMapper,
    private val getHomeTweets: GetHomeTweets
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeTweetsViewModel(
            tweetMapper,
            getHomeTweets
        ) as T
    }
}