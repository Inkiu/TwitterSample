package com.inkiu.data.entities

import com.inkiu.data.entities.entities.UserEntities
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "id_str") val id: String = "",
    @Json(name = "name") val name: String = "",
    @Json(name = "screen_name") val screenName: String = "",
    @Json(name = "location") val location: String = "",

    @Json(name = "url") val _url: String? = null,

    @Json(name = "description") val description: String = "",
    @Json(name = "verified") val verified: Boolean = false,
    @Json(name = "followers_count") val followerCount: Int = 0,
    @Json(name = "friends_count") val friendCount: Int = 0,
    @Json(name = "favourites_count") val favoriteCount: Int = 0,
    @Json(name = "statuses_count") val statusCount: Int = 0,
    @Json(name = "created_at") val createTime: String = "",

    @Json(name = "profile_banner_url") val profileBannerUrl: String = "",
    @Json(name = "profile_image_url_https") val profileImageUrl: String = "",
    @Json(name = "default_profile") val defaultProfile: Boolean = true,
    @Json(name = "default_profile_image") val defaultProfileImage: Boolean = true,

    @Json(name = "entities") val entities: UserEntities = UserEntities()

) {
    val url: String
        get() = _url ?: ""
}