package com.inkiu.twittersample.ui.common.tweets.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.inkiu.data.mapper.Mapper
import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.domain.usecase.GetUserTweets
import com.inkiu.twittersample.ui.common.model.Tweet
import com.inkiu.twittersample.ui.common.model.mapper.TweetEntityTweetMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserTweetDataSource(
    private val getUserTweets: GetUserTweets,
    private val scope: CoroutineScope,
    private val mapper: TweetEntityTweetMapper
) : ItemKeyedDataSource<Long, Tweet>(),
    Mapper<TweetEntity, Tweet> by mapper {

    val state: MutableLiveData<DataSourceState> = MutableLiveData(DataSourceState.Init)

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Tweet>
    ) {
        val initialKey= params.requestedInitialKey ?: 0L
        scope.launch {
            state.value = DataSourceState.LoadingInitial
        }
        load(initialKey, params.requestedLoadSize, callback)
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Tweet>) {
        val key = params.key
        scope.launch {
            state.value = DataSourceState.Loading
        }
        load(key, params.requestedLoadSize, callback)
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Tweet>) {
        // no implementation
    }

    private fun load(startId: Long, size: Int, callback: LoadCallback<Tweet>) {
        scope.launch {
            launch {
                val result = runCatching {
                    getUserTweets.execute(
                        GetUserTweets.Param(-1L, startId, size)
                    )
                }
                if (result.isSuccess) {
                    state.value = DataSourceState.Success
                    val tweets = (result.getOrNull() ?: emptyList()).map { map(it) }
                    callback.onResult(tweets)
                } else {
                    state.value = DataSourceState.Failure(result.exceptionOrNull())
                    callback.onResult(emptyList())
                }
            }
        }
    }

    override fun getKey(item: Tweet): Long = item.id
}