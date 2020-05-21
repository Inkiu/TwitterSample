package com.inkiu.twittersample.ui.common.datasource

import com.inkiu.domain.Constant
import com.inkiu.domain.usecase.GetUserTweets
import com.inkiu.twittersample.model.mapper.TweetEntityTweetMapper
import kotlinx.coroutines.CoroutineScope

class UserTweetDataSource(
    private val userId: Long,
    getUserTweets: GetUserTweets,
    scope: CoroutineScope,
    mapper: TweetEntityTweetMapper
) : TweetListDataSource<GetUserTweets.Param>(
    getUserTweets,
    scope,
    mapper
) {
    override fun getParam(startId: Long, size: Int): GetUserTweets.Param {
        return GetUserTweets.Param(userId, startId, size)
    }
    override fun getInitialKey(): Long = Constant.INVALID_ID
}