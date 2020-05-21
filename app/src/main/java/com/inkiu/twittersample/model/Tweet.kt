package com.inkiu.twittersample.model

import android.text.Spannable
import java.util.*

data class Tweet(
    val id: Long,
    val user: User,
    val content: Spannable,
    val createdDate: Date,
    val place: String,

    val commentCount: Int,
    val reTweetCount: Int,
    val likeCount: Int,

    val liked: Boolean,
    val retweeted: Boolean,

    val media: Media?,
    val quoted: Quoted?
) {
    fun isMediaContained() = media != null
    fun isQuotedContained() = quoted != null
}