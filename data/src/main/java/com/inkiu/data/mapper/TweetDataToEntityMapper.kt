package com.inkiu.data.mapper

import com.inkiu.data.entities.TweetData
import com.inkiu.data.entities.entities.TweetEntities
import com.inkiu.data.entities.entities.UserMention
import com.inkiu.domain.entities.tweet.ReTweetEntity
import com.inkiu.domain.entities.tweet.SimpleTweetEntity
import com.inkiu.domain.entities.tweet.TextComposeEntity
import com.inkiu.domain.entities.tweet.TweetEntity

class TweetDataToEntityMapper(
    private val dateMapper: UTCStringToDateMapper,
    private val mediaMapper: MediaDataToEntityMapper,
    private val hashTagMapper: HashTagDataToEntityMapper,
    private val userMentionMapper: UserMentionDataToEntityMapper,
    private val symbolMapper: SymbolDataToEntityMapper
) : Mapper<TweetData, TweetEntity> {

    override fun map(src: TweetData): TweetEntity {
        val isReTweetContained = src.reTweet.id == ""
        return if (isReTweetContained) createReTweet(src) else createSimpleTweet(src)
    }

    private fun createSimpleTweet(src: TweetData): SimpleTweetEntity {
        return SimpleTweetEntity(
            userIndex = src.user.id.toLong(),
            content = src.text,
            createdDate = dateMapper.map(src.created),
            place = src.place.fullName, // TODO - 언어
            commentCount = 0, // TODO
            reTweetCount = src.reTweetCount,
            likeCount = src.favoriteCount,
            liked = src.favorited,
            reTweeted = src.reTweeted,
            textComposeEntities = getTextComposites(src.entities),
            media = src.extendedEntities.medias.map { mediaMapper.map(it) }
        )
    }

    private fun createReTweet(src: TweetData): ReTweetEntity {
        return ReTweetEntity(
            userIndex = src.user.id.toLong(),
            content = src.text,
            createdDate = dateMapper.map(src.created),
            place = src.place.fullName, // TODO - 언어
            commentCount = 0, // TODO
            reTweetCount = src.reTweetCount,
            likeCount = src.favoriteCount,
            liked = src.favorited,
            reTweeted = src.reTweeted,
            textComposeEntities = getTextComposites(src.entities),
            media = src.extendedEntities.medias.map { mediaMapper.map(it) },
            sourceTweet = createSimpleTweet(src.reTweet) // NOTE 리트윗은 simple
        )
    }
    
    private fun getTextComposites(src: TweetEntities): List<TextComposeEntity> {
        return src.hashTags.map { hashTagMapper.map(it) } +
                src.userMentions.map { userMentionMapper.map(it) } +
                src.symbols.map { symbolMapper.map(it) }
    }

}