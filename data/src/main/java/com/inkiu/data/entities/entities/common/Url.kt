package com.inkiu.data.entities.entities.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Url(
    @Json(name = "expanded_url") val fullUrl: String = "",
    @Json(name = "display_url") val displayUrl: String = ""
)