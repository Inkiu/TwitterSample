package com.inkiu.data.entities.entities.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Place(
    @Json(name = "id") val id: String = "",
    // ??
    @Json(name = "url") val url: String = "",
    // place_type - city
    @Json(name = "place_type") val type: String = "",
    // name
    @Json(name = "name") val name: String = "",
    // full_name
    @Json(name = "full_name") val fullName: String = "",
    // country_code
    @Json(name = "country_code") val countryCode: String = "",
    // country
    @Json(name = "country") val country: String = ""
)