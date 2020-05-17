package com.inkiu.data.entities.entities.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    @Json(name = "id_str") val id: String = "",
    // ???
    @Json(name = "indices") val indices: List<Long> = emptyList(),
    // media url
    @Json(name = "media_url_https") val url: String = "",
    // media type - animated_gif, photo, video
    @Json(name = "type") val type: String = "",
    // media size
    @Json(name = "sizes") val sizes: Sizes = Sizes()
)