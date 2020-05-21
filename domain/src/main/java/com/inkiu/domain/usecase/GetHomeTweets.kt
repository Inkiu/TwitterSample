package com.inkiu.domain.usecase

import com.inkiu.domain.entities.tweet.ReTweetEntity
import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.domain.entities.user.UserEntity
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository

class GetHomeTweets(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository
) : SingleUseCase<GetHomeTweets.Param, List<TweetEntity>> {

    override suspend fun execute(param: Param): List<TweetEntity> {
        return tweetRepository.getHomeTweets(param.startIndex, param.count)
    }

    data class Param(
        val startIndex: Long,
        val count: Int
    )
}