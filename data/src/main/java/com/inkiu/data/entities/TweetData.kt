package com.inkiu.data.entities

import com.inkiu.data.entities.entities.TweetEntities
import com.inkiu.data.entities.entities.TweetExtendedEntities
import com.inkiu.data.entities.entities.common.Place
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TweetData(
    @Json(name = "created_at") val created: String = "",
    @Json(name = "id_str") val id: String = "",

    @Json(name = "text") val text: String = "",
    @Json(name = "display_text_range") val displayTextRange: List<Int> = emptyList(),

    @Json(name = "entities") val entities: TweetEntities = TweetEntities(),
    @Json(name = "extended_entities") val extendedEntities: TweetExtendedEntities = TweetExtendedEntities(),

    @Json(name = "user") val user: UserData = UserData(),

    @Json(name = "quoted_status") val _reTweet: TweetData? = null,

    @Json(name = "place") val _place: Place? = null
) {
    val place: Place
        get() = _place ?: Place()

    val reTweet: TweetData
        get() = _reTweet ?: TweetData()
}