package com.inkiu.domain.usecase

import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.domain.repositoty.TweetRepository

class GetTweet(
    private val tweetRepository: TweetRepository
) : SingleUseCase<GetTweet.Param, TweetEntity> {

    override suspend fun execute(param: Param): TweetEntity {
        return tweetRepository.getTweet(param.tweetIndex)
    }

    data class Param(
        val tweetIndex: Long
    )
}