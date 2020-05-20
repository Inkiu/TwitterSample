package com.inkiu.data.entities

import com.inkiu.data.entities.entities.TweetEntities
import com.inkiu.data.entities.entities.TweetExtendedEntities
import com.inkiu.data.entities.entities.common.PlaceData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TweetData(
    @Json(name = "created_at") val created: String = "",
    @Json(name = "id") val id: Long = -1L,

    @Json(name = "full_text") val text: String = "",
    @Json(name = "display_text_range") val displayTextRange: List<Int> = emptyList(),

    @Json(name = "entities") val entities: TweetEntities = TweetEntities(),
    @Json(name = "extended_entities") val extendedEntities: TweetExtendedEntities = TweetExtendedEntities(),

    @Json(name = "user") val user: UserData = UserData(),

    @Json(name = "in_reply_to_status_id") val replyToTweetId: Long = -1L,

    @Json(name = "quoted_status") val _reTweet: TweetData? = null,

    @Json(name = "place") val _place: PlaceData? = null,

    @Json(name = "retweet_count") val reTweetCount: Int = 0,
    @Json(name = "favorite_count") val favoriteCount: Int = 0,
    @Json(name = "favorited") val favorited: Boolean = false,
    @Json(name = "retweeted") val reTweeted: Boolean = false
) {
    val place: PlaceData
        get() = _place ?: PlaceData()

    val reTweet: TweetData
        get() = _reTweet ?: TweetData()
}