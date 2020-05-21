package com.inkiu.twittersample.ui.common.datasource

import com.inkiu.domain.usecase.GetReplyTweets
import com.inkiu.twittersample.model.mapper.TweetEntityTweetMapper
import kotlinx.coroutines.CoroutineScope

class ReplyTweetDataSource(
    private val targetTweetId: Long,
    private val userName: String,
    getReplyTweets: GetReplyTweets,
    scope: CoroutineScope,
    mapper: TweetEntityTweetMapper
) : TweetListDataSource<GetReplyTweets.Param>(
    getReplyTweets,
    scope,
    mapper
) {
    override fun getParam(startId: Long, size: Int): GetReplyTweets.Param {
        return GetReplyTweets.Param(userName, targetTweetId, startId, size)
    }
    override fun getInitialKey(): Long = targetTweetId
}