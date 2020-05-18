package com.inkiu.domain.usecase

import com.inkiu.domain.entities.TweetModel
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import io.reactivex.Single

class GetHomeTweets(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository
) : SingleUseCase<GetHomeTweets.Param, List<TweetModel>> {

    override suspend fun execute(param: Param): List<TweetModel> {
        val homeTweets = tweetRepository.getHomeTweets(param.startIndex, param.count)
        val users = userRepository.getUsers(homeTweets.map { it.userIndex })
        return homeTweets.zip(users).map {
            TweetModel(it.first, it.second)
        }
    }

    data class Param(
        val startIndex: Long,
        val count: Int
    )
}