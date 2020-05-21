package com.inkiu.twittersample.ui.common.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.inkiu.data.mapper.Mapper
import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.domain.usecase.SingleUseCase
import com.inkiu.twittersample.model.Tweet
import com.inkiu.twittersample.model.mapper.TweetEntityTweetMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class TweetListDataSource <P> (
    private val getTweets: SingleUseCase<P, List<TweetEntity>>,
    private val scope: CoroutineScope,
    val mapper: TweetEntityTweetMapper
) : ItemKeyedDataSource<Long, Tweet>(),
    Mapper<TweetEntity, Tweet> by mapper {

    val state: MutableLiveData<DataSourceState> = MutableLiveData(DataSourceState.Init)

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Tweet>
    ) {
        val initialKey= getInitialKey()
        scope.launch {
            state.value = DataSourceState.LoadingInitial
            load(
                initialKey,
                params.requestedLoadSize,
                callback.wrap { if (it.isEmpty()) state.value = DataSourceState.Empty }
            )
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Tweet>) {
        val key = params.key
        scope.launch {
            state.value = DataSourceState.Loading
            load(key, params.requestedLoadSize, callback)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Tweet>) {
        // no implementation
    }

    private suspend fun load(start: Long, size: Int, callback: LoadCallback<Tweet>) {
        getTweets(getParam(start, size))
            .onSuccess { ret ->
                state.value = DataSourceState.Success
                callback.onResult(ret.map { map(it) })
            }
            .onFailure {
                state.value = DataSourceState.Failure(it)
                callback.onResult(emptyList())
            }
    }

    override fun getKey(item: Tweet): Long = item.id

    abstract fun getParam(startId: Long, size: Int): P
    abstract fun getInitialKey(): Long

    private fun LoadCallback<Tweet>.wrap(func: (result: List<Tweet>) -> Unit)
        = CallbackWrapper(this, func)

    class CallbackWrapper(
        private val callback: LoadCallback<Tweet>,
        private val func: (result: List<Tweet>) -> Unit
    ) : LoadCallback<Tweet>() {
        override fun onResult(data: MutableList<Tweet>) {
            callback.onResult(data)
            func(data)
        }
    }
}