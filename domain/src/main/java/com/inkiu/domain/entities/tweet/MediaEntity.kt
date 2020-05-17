package com.inkiu.domain.entities.tweet

data class MediaEntity(
    val id: Long,
    val mediaUrl: String,
    val type: Type
) {
    enum class Type {
        Photo, Video, Animated, Unknown
    }
}