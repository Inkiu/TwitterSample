package com.inkiu.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserEntities(
    @Json(name = "description") val description: DescriptionData = DescriptionData(),
    @Json(name = "url") val userUrl: UserUrlData = UserUrlData()
)

@JsonClass(generateAdapter = true)
data class DescriptionData(
    @Json(name = "urls") val urls: List<UrlData> = emptyList()
)

// TODO - rename
@JsonClass(generateAdapter = true)
data class UserUrlData(
    @Json(name = "urls") val urls: List<UrlData> = emptyList()
)