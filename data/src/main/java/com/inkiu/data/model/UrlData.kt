package com.inkiu.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UrlData(
    @Json(name = "expanded_url") val fullUrl: String = "",
    @Json(name = "display_url") val displayUrl: String = ""
)