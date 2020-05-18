package com.inkiu.data.entities.entities.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SizesData(
    @Json(name = "thumb") val thumb: SizeData = SizeData(),
    @Json(name = "small") val small: SizeData = SizeData(),
    @Json(name = "medium") val medium: SizeData = SizeData(),
    @Json(name = "large") val large: SizeData = SizeData()
)

@JsonClass(generateAdapter = true)
data class SizeData(
    @Json(name = "w") val width: Int = 0,
    @Json(name = "h") val height: Int = 0,
    @Json(name = "resize") val resizeType: String = ""
)

