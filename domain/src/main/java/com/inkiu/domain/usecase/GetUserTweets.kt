package com.inkiu.domain.usecase

import com.inkiu.domain.entities.TweetModel
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import io.reactivex.Single

class GetUserTweets(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository
) : SingleUseCase<GetUserTweets.Param, List<TweetModel>> {

    override suspend fun execute(param: Param): List<TweetModel> {
        val userTweets = tweetRepository.getUserTweets(param.userIndex, param.startIndex, param.count)
        val user = userRepository.getUser(param.userIndex)
        return userTweets.map {
            TweetModel(it, user)
        }
    }

    data class Param(
        val userIndex: Long,
        val startIndex: Long,
        val count: Int
    )
}