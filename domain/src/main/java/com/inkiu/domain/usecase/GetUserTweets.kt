package com.inkiu.domain.usecase

import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import java.lang.RuntimeException

class GetUserTweets(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository
) : SingleUseCase<GetUserTweets.Param, List<TweetEntity>> {

    override suspend fun execute(param: Param): List<TweetEntity> {
        return tweetRepository.getUserTweets(param.userIndex, param.startIndex, param.count)
    }

    data class Param(
        val userIndex: Long,
        val startIndex: Long,
        val count: Int
    )
}