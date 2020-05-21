package com.inkiu.twittersample.ui.common.datasource

import com.inkiu.domain.Constant
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.twittersample.model.mapper.TweetEntityTweetMapper
import kotlinx.coroutines.CoroutineScope
import java.lang.RuntimeException

class HomeTweetDataSource(
    getHomeTweets: GetHomeTweets,
    scope: CoroutineScope,
    mapper: TweetEntityTweetMapper
) : TweetListDataSource<GetHomeTweets.Param>(getHomeTweets, scope, mapper) {

    override fun getParam(startId: Long, size: Int): GetHomeTweets.Param {
        return GetHomeTweets.Param(startId, size)
    }

    override fun getInitialKey(): Long = Constant.INVALID_ID
}