package com.inkiu.domain.usecase

import com.inkiu.domain.entities.TweetModel
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import io.reactivex.Single

class GetUserTweets(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository
) : SingleUseCase<GetUserTweets.Param, List<TweetModel>> {

    override fun execute(param: Param): Single<List<TweetModel>> {
        return tweetRepository.getUserTweets(param.userIndex, param.startIndex, param.count)
            .flatMap { tweetEntities ->
                userRepository.getUser(param.userIndex)
                    .map { userEntity ->
                        tweetEntities.map { TweetModel(it, userEntity) }
                    }
            }
    }

    data class Param(
        val userIndex: Long,
        val startIndex: Long,
        val count: Int
    )
}