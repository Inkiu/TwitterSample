package com.inkiu.domain.usecase

import com.inkiu.domain.entities.TweetModel
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import io.reactivex.Single

class GetHomeTweets(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository
) : SingleUseCase<GetHomeTweets.Param, List<TweetModel>> {

    override fun execute(param: Param): Single<List<TweetModel>> {
        return tweetRepository.getHomeTweets(param.startIndex, param.count)
            .flatMap { tweetEntities ->
                userRepository.getUsers(tweetEntities.map { it.userIndex })
                    .map { users ->
                        tweetEntities.zip(users).map { TweetModel(it.first, it.second) }
                    }
            }
    }

    data class Param(
        val startIndex: Long,
        val count: Int
    )
}