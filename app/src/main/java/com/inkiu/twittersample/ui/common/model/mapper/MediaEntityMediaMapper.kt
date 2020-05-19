package com.inkiu.twittersample.ui.common.model.mapper

import com.inkiu.data.mapper.Mapper
import com.inkiu.domain.entities.tweet.MediaEntity
import com.inkiu.twittersample.ui.common.model.*
import java.lang.IllegalArgumentException

class MediaEntityMediaMapper : Mapper<List<MediaEntity>, Media> {

    override fun map(src: List<MediaEntity>): Media {
        if (src.isEmpty()) throw IllegalArgumentException("MediaEntity 하나 이상이어야 함.")
        return getAnimatedMedia(src)
            ?: getVideoMedia(src)
            ?: getPhotosMedia(src)
            ?: UnknownMedia
    }

    private fun getAnimatedMedia(src: List<MediaEntity>): Media? {
        // GIF 는 하나만 처리
        return src.firstOrNull { it.type == MediaEntity.Type.Animated }
            ?.let { AnimatedMedia(it.id, it.mediaUrl) }
    }

    private fun getVideoMedia(src: List<MediaEntity>): Media? {
        // Video 는 하나만 처리
        return src.firstOrNull { it.type == MediaEntity.Type.Video }
            ?.let { VideoMedia(it.id, it.mediaUrl) }
    }

    private fun getPhotosMedia(src: List<MediaEntity>): Media? {
        val medias = src.filter { it.type == MediaEntity.Type.Photo }
            .map { PhotoMedia(it.id, it.mediaUrl) }
        return if (medias.isEmpty()) null else PhotosMedia(medias)
    }
}