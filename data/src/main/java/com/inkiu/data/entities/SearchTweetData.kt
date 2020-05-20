package com.inkiu.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchTweetData(
    @Json(name = "statuses") val tweetDataList: List<TweetData> = emptyList()
)