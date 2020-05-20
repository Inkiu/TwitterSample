package com.inkiu.twittersample.ui.common.model

interface Media

data class PhotoMedia(
    val id: Long,
    val sourceUrl: String
) : Media

data class PhotosMedia(
    val photos: List<PhotoMedia>
) : Media

data class AnimatedMedia(
    val id: Long,
    val sourceUrl: String
) : Media

data class VideoMedia(
    val id: Long,
    val sourceUrl: String
) : Media

object UnknownMedia : Media