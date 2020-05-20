package com.inkiu.twittersample.ui.common.model.mapper

import com.inkiu.data.mapper.Mapper
import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.twittersample.ui.common.model.Quoted
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TweetEntityQuotedMapper @Inject constructor(
    private val userMapper: UserEntityUserMapper
) : Mapper<TweetEntity, Quoted> {
    override fun map(src: TweetEntity): Quoted {
        return Quoted(
            id = src.id,
            user = userMapper.map(src.userEntity),
            content = src.content,
            createdDate = src.createdDate
        )
    }
}