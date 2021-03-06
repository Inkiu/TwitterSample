package com.inkiu.twittersample.ui.home

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.domain.usecase.GetUser
import com.inkiu.twittersample.di.PerActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.model.Tweet
import com.inkiu.twittersample.model.User
import com.inkiu.twittersample.model.mapper.TweetEntityTweetMapper
import com.inkiu.twittersample.model.mapper.UserEntityUserMapper
import com.inkiu.twittersample.ui.common.LoadingState
import com.inkiu.twittersample.ui.common.datasource.HomeTweetDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel(
    private val tweetMapper: TweetEntityTweetMapper,
    private val userMapper: UserEntityUserMapper,
    private val getHomeTweets: GetHomeTweets,
    private val getUser: GetUser
) : BaseViewModel() {

    private val dataSourceData = MutableLiveData<HomeTweetDataSource>()
    private val dataSourceStateData = dataSourceData.switchMap { it.state }
    private val getUserStateData = MutableLiveData<LoadingState>()

    // exposed live data
    val pagingListData = dataSourceData.map { createPagedList(it) }
    val userData = MutableLiveData<User>()

    val networkState = MediatorLiveData<LoadingState>().apply {
        addSource(dataSourceStateData) { value = it }
        addSource(getUserStateData) { value = it }
    }

    override fun onAttached() {
        launch { refreshUser() }
        refreshTweets()
    }

    fun refresh() {
        refreshTweets()
    }

    private suspend fun refreshUser() {
        getUser(GetUser.Param.MyProfile)
            .onSuccess {
                userData.value = userMapper.map(it)
            }
            .onFailure {
                getUserStateData.value = LoadingState.Failure(it)
            }
    }

    private fun refreshTweets() {
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
    private val userMapper: UserEntityUserMapper,
    private val getHomeTweets: GetHomeTweets,
    private val getUser: GetUser
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
            tweetMapper,
            userMapper,
            getHomeTweets,
            getUser
        ) as T
    }
}