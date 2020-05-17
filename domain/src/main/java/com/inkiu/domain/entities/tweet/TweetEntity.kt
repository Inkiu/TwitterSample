package com.inkiu.domain.entities.tweet

import java.util.*

sealed class TweetEntity(
    open val userIndex: Long,
    open val content: String,
    open val createdDate: Date,
    open val place: String,

    open val commentCount: Int,
    open val reTweetCount: Int,
    open val likeCount: Int,

    open val textComposeEntities: List<TextComposeEntity>,
    open val media: List<MediaEntity>
)

data class SimpleTweetEntity(
    override val userIndex: Long,
    override val content: String,
    override val createdDate: Date,
    override val place: String,

    override val commentCount: Int,
    override val reTweetCount: Int,
    override val likeCount: Int,

    override val textComposeEntities: List<TextComposeEntity>,
    override val media: List<MediaEntity>
) : TweetEntity(
    userIndex,
    content,
    createdDate,
    place,
    commentCount,
    reTweetCount,
    likeCount,
    textComposeEntities,
    media
)

data class ReTweetEntity(
    override val userIndex: Long,
    override val content: String,
    override val createdDate: Date,
    override val place: String,

    override val commentCount: Int,
    override val reTweetCount: Int,
    override val likeCount: Int,

    override val textComposeEntities: List<TextComposeEntity>,
    override val media: List<MediaEntity>,

    val sourceTweet: TweetEntity
) : TweetEntity(
    userIndex,
    content,
    createdDate,
    place,
    commentCount,
    reTweetCount,
    likeCount,
    textComposeEntities,
    media
)