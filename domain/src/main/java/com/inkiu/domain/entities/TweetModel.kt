package com.inkiu.domain.entities

import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.domain.entities.user.UserEntity

data class TweetModel(
    val tweetEntity: TweetEntity,
    val userEntity: UserEntity
)