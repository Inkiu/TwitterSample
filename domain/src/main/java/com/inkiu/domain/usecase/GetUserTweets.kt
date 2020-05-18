package com.inkiu.domain.usecase

import com.inkiu.domain.entities.UserAndTweetEntity
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository

class GetUserTweets(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository
) : SingleUseCase<GetUserTweets.Param, List<UserAndTweetEntity>> {

    override suspend fun execute(param: Param): List<UserAndTweetEntity> {
        val userTweets = tweetRepository.getUserTweets(param.userIndex, param.startIndex, param.count)
        val user = userRepository.getUser(param.userIndex)
        return userTweets.map {
            UserAndTweetEntity(it, user)
        }
    }

    data class Param(
        val userIndex: Long,
        val startIndex: Long,
        val count: Int
    )
}