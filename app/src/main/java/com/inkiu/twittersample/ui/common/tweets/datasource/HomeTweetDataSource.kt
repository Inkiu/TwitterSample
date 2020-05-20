package com.inkiu.twittersample.ui.common.tweets.datasource

import com.inkiu.domain.Constant
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.twittersample.ui.common.model.mapper.TweetEntityTweetMapper
import kotlinx.coroutines.CoroutineScope

class HomeTweetDataSource(
    getHomeTweets: GetHomeTweets,
    scope: CoroutineScope,
    mapper: TweetEntityTweetMapper
) : TweetListDataSource<GetHomeTweets.Param>(getHomeTweets, scope, mapper) {

    override fun getParam(startId: Long, size: Int): GetHomeTweets.Param {
        return GetHomeTweets.Param(startId, size)
    }

    override fun getInitialKey(): Long = Constant.INVALID_ID

//    val state: MutableLiveData<DataSourceState> = MutableLiveData(DataSourceState.Init)
//
//    override fun loadInitial(
//        params: LoadInitialParams<Long>,
//        callback: LoadInitialCallback<Tweet>
//    ) {
//        val initialKey= params.requestedInitialKey ?: 0L
//        scope.launch {
//            state.value = DataSourceState.LoadingInitial
//        }
//        load(initialKey, params.requestedLoadSize, callback)
//    }
//
//    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Tweet>) {
//        val key = params.key
//        scope.launch {
//            state.value = DataSourceState.Loading
//        }
//        load(key, params.requestedLoadSize, callback)
//    }
//
//    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Tweet>) {
//        // no implementation
//    }
//
//    private fun load(startId: Long, size: Int, callback: LoadCallback<Tweet>) {
//        scope.launch {
//            launch {
//                val result = runCatching {
//                    getHomeTweets.execute(
//                        GetHomeTweets.Param(startId, size)
//                    )
//                }
//                if (result.isSuccess) {
//                    state.value = DataSourceState.Success
//                    val tweets = (result.getOrNull() ?: emptyList()).map { map(it) }
//                    callback.onResult(tweets)
//                } else {
//                    state.value = DataSourceState.Failure(result.exceptionOrNull())
//                    callback.onResult(emptyList())
//                }
//            }
//        }
//    }
//
//    override fun getKey(item: Tweet): Long = item.id
}