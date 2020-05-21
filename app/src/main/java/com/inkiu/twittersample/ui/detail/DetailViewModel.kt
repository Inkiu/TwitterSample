package com.inkiu.twittersample.ui.detail

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.inkiu.domain.entities.tweet.SimpleTweetEntity
import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.domain.usecase.*
import com.inkiu.twittersample.di.PerActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.model.Tweet
import com.inkiu.twittersample.ui.common.model.mapper.TweetEntityTweetMapper
import com.inkiu.twittersample.ui.common.model.mapper.UserDetailEntityToUserDetailMapper
import com.inkiu.twittersample.ui.common.model.mapper.UserEntityUserMapper
import com.inkiu.twittersample.ui.common.tweets.datasource.NewUserTweetDataSource
import com.inkiu.twittersample.ui.common.tweets.datasource.ReplyTweetDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class DetailViewModel(
    private val userId: Long,
    private val getUserDetail: GetUserDetail,
    private val getUserTweets: GetUserTweets,
    private val tweetMapper: TweetEntityTweetMapper,
    private val userMapper: UserDetailEntityToUserDetailMapper
) : BaseViewModel() {

    private val _detailData = MutableLiveData<Tweet>()
//    private val dataSourceData = _detailData.map { createDataSource() }
    private val dataSourceData = MutableLiveData<NewUserTweetDataSource>()

    val detailData = _detailData.map { it }
    val pagingListData = dataSourceData.map { createPagedList(it) }
    val networkStateData = dataSourceData.switchMap { it.state }

    override fun onAttached() {
        launch { getTweetDetail() }
    }
    
    private fun getTweetDetail() {
        launch {
//            _detailData.value = SimpleTweetEntity().let { tweetMapper(it) }
            dataSourceData.value = createDataSource()
        }
    }

    private fun createDataSource(): NewUserTweetDataSource {
        return NewUserTweetDataSource(userId, getUserTweets, viewModelScope, tweetMapper)
    }

    private fun createPagedList(dataSource: NewUserTweetDataSource): PagedList<Tweet> {
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
    @Named("user_id") private val userId: Long,
    private val getUserDetail: GetUserDetail,
    private val getUserTweets: GetUserTweets,
    private val tweetMapper: TweetEntityTweetMapper,
    private val userMapper: UserDetailEntityToUserDetailMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(
            userId,
            getUserDetail,
            getUserTweets,
            tweetMapper,
            userMapper
        ) as T
    }
}