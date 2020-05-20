package com.inkiu.domain.entities.tweet

import com.inkiu.domain.entities.user.UserEntity
import java.util.*

sealed class TweetEntity(
    open val id: Long,
    open val userEntity: UserEntity,
    open val content: String,
    open val createdDate: Date,
    open val place: String,

    open val commentCount: Int,
    open val reTweetCount: Int,
    open val likeCount: Int,

    open val replyToId: Long,

    open val liked: Boolean,
    open val reTweeted: Boolean,

    open val textComposeEntities: List<TextComposeEntity>,
    open val media: List<MediaEntity>
)

data class SimpleTweetEntity(
    override val id: Long,
    override val userEntity: UserEntity,
    override val content: String,
    override val createdDate: Date,
    override val place: String,

    override val commentCount: Int,
    override val reTweetCount: Int,
    override val likeCount: Int,

    override val replyToId: Long,

    override val liked: Boolean,
    override val reTweeted: Boolean,

    override val textComposeEntities: List<TextComposeEntity>,
    override val media: List<MediaEntity>
) : TweetEntity(
    id,
    userEntity,
    content,
    createdDate,
    place,
    commentCount,
    reTweetCount,
    likeCount,
    replyToId,
    liked,
    reTweeted,
    textComposeEntities,
    media
)

data class ReTweetEntity(
    override val id: Long,
    override val userEntity: UserEntity,
    override val content: String,
    override val createdDate: Date,
    override val place: String,

    override val commentCount: Int,
    override val reTweetCount: Int,
    override val likeCount: Int,

    override val replyToId: Long,

    override val liked: Boolean,
    override val reTweeted: Boolean,

    override val textComposeEntities: List<TextComposeEntity>,
    override val media: List<MediaEntity>,

    val sourceTweet: TweetEntity
) : TweetEntity(
    id,
    userEntity,
    content,
    createdDate,
    place,
    commentCount,
    reTweetCount,
    likeCount,
    replyToId,
    liked,
    reTweeted,
    textComposeEntities,
    media
)