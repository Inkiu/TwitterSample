package com.inkiu.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TweetEntities(
    @Json(name = "hashtags") val hashTags: List<HashTag> = emptyList(),
    @Json(name = "user_mentions") val userMentions: List<UserMention> = emptyList(),
    @Json(name = "symbols") val symbols: List<Symbol> = emptyList()
)

@JsonClass(generateAdapter = true)
data class TweetExtendedEntities(
    @Json(name = "media") val medias: List<MediaData> = emptyList()
)

@JsonClass(generateAdapter = true)
data class HashTag(
    @Json(name = "text") val text: String = "",
    @Json(name = "indices") val indices: List<Int> = emptyList()
)

@JsonClass(generateAdapter = true)
data class UserMention(
    @Json(name = "name") val name: String = "",
    @Json(name = "screen_name") val screenName: String = "",
    @Json(name = "id") val id: Long = 0L,
    @Json(name = "indices") val indices: List<Int> = emptyList()
)

@JsonClass(generateAdapter = true)
data class Symbol(
    @Json(name = "text") val text: String = "",
    @Json(name = "indices") val indices: List<Int> = emptyList()
)

@JsonClass(generateAdapter = true)
data class Poll(
    @Json(name = "options") val options: List<Option> = emptyList(),
    @Json(name = "end_datetime") val endDatetime: String = "",
    @Json(name = "duration_minutes") val durationMinutes: Int = 0
) {
    @JsonClass(generateAdapter = true)
    data class Option(
        @Json(name = "position") val position: Int = 0,
        @Json(name = "text") val text: String = ""
    )
}