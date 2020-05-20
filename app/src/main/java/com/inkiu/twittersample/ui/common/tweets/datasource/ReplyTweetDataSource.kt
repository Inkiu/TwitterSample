package com.inkiu.twittersample.ui.common.tweets.datasource

import com.inkiu.domain.usecase.GetReplyTweets
import com.inkiu.twittersample.ui.common.model.Tweet
import com.inkiu.twittersample.ui.common.model.mapper.TweetEntityTweetMapper
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