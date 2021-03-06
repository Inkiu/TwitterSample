package com.inkiu.data.model

import com.inkiu.domain.Constant
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TweetData(
    @Json(name = "created_at") val created: String = "",
    @Json(name = "id") val id: Long = Constant.INVALID_ID,

    @Json(name = "full_text") val text: String = "",
    @Json(name = "display_text_range") val displayTextRange: List<Int> = emptyList(),

    @Json(name = "entities") val entities: TweetEntities = TweetEntities(),
    @Json(name = "extended_entities") val extendedEntities: TweetExtendedEntities = TweetExtendedEntities(),

    @Json(name = "user") val user: UserData = UserData(),

    @Json(name = "in_reply_to_status_id") val _replyToTweetId: Long? = null,

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

    val replyToTweetId: Long
        get() = _replyToTweetId ?: Constant.INVALID_ID
}