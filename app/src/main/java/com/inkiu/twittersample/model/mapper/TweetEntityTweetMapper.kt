package com.inkiu.twittersample.model.mapper

import com.inkiu.data.mapper.Mapper
import com.inkiu.domain.entities.tweet.ReTweetEntity
import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.twittersample.model.Tweet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TweetEntityTweetMapper @Inject constructor(
    private val userEntityUserMapper: UserEntityUserMapper,
    private val tweetEntityQuotedMapper: TweetEntityQuotedMapper,
    private val textComposeToSpannableMapper: TextComposeToSpannableMapper,
    private val mediaEntityMediaMapper: MediaEntityMediaMapper
) : Mapper<TweetEntity, Tweet> {

    override fun map(src: TweetEntity): Tweet {
        return Tweet(
            id = src.id,
            user = userEntityUserMapper.map(src.userEntity),
            content = textComposeToSpannableMapper.map(src.content to src.textComposeEntities),
            createdDate = src.createdDate,
            commentCount = src.commentCount,
            reTweetCount = src.reTweetCount,
            place = src.place,
            likeCount = src.likeCount,
            liked = src.liked,
            retweeted = src.reTweeted,
            media = if (src.media.isEmpty()) null else mediaEntityMediaMapper.map(src.media),
            quoted = if (src is ReTweetEntity) tweetEntityQuotedMapper.map(src.sourceTweet) else null
        )
    }

}