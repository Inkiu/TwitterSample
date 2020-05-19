package com.inkiu.twittersample.ui.common.model.mapper

import com.inkiu.data.mapper.Mapper
import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.twittersample.ui.common.model.QuotedTweet

class TweetEntityQuotedMapper(
    private val userMapper: UserEntityUserMapper
) : Mapper<TweetEntity, QuotedTweet> {
    override fun map(src: TweetEntity): QuotedTweet {
        return QuotedTweet(
            id = src.id,
            user = userMapper.map(src.userEntity),
            content = src.content,
            createdDate = src.createdDate
        )
    }
}