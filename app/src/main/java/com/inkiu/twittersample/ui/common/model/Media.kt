package com.inkiu.twittersample.ui.common.model

interface Media

interface SingleMedia : Media {
    val id: Long
    val sourceUrl: String
}

data class PhotoMedia(
    val id: Long,
    val sourceUrl: String
) : Media

data class PhotosMedia(
    val photos: List<PhotoMedia>
) : Media

data class AnimatedMedia(
    override val id: Long,
    override val sourceUrl: String
) : SingleMedia

data class VideoMedia(
    override val id: Long,
    override val sourceUrl: String
) : SingleMedia

object UnknownMedia : Media