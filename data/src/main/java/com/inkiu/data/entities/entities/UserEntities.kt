package com.inkiu.data.entities.entities

import com.inkiu.data.entities.entities.common.Url
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserEntities(
    @Json(name = "description") val description: Description = Description(),
    @Json(name = "url") val userUrl: UserUrl = UserUrl()
)

@JsonClass(generateAdapter = true)
data class Description(
    @Json(name = "urls") val urls: List<Url> = emptyList()
)

// TODO - rename
@JsonClass(generateAdapter = true)
data class UserUrl(
    @Json(name = "urls") val urls: List<Url> = emptyList()
)