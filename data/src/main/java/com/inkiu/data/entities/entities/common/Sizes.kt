package com.inkiu.data.entities.entities.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sizes(
    @Json(name = "thumb") val thumb: Size = Size(),
    @Json(name = "small") val small: Size = Size(),
    @Json(name = "medium") val medium: Size = Size(),
    @Json(name = "large") val large: Size = Size()
)

@JsonClass(generateAdapter = true)
data class Size(
    @Json(name = "w") val width: Int = 0,
    @Json(name = "h") val height: Int = 0,
    @Json(name = "resize") val resizeType: String = ""
)

