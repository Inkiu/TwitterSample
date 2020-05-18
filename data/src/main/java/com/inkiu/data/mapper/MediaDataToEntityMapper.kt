package com.inkiu.data.mapper

import com.inkiu.data.entities.entities.common.Media
import com.inkiu.domain.entities.tweet.MediaEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaDataToEntityMapper @Inject constructor()
    : Mapper<Media, MediaEntity> {

    override fun map(src: Media): MediaEntity {
        return MediaEntity(
            id = src.id,
            mediaUrl = src.url,
            type = getType(src.type)
        )
    }

    private fun getType(str: String): MediaEntity.Type {
        return when (str) {
            "animated_gif" -> MediaEntity.Type.Animated
            "photo" -> MediaEntity.Type.Photo
            "video" -> MediaEntity.Type.Video
            else -> MediaEntity.Type.Unknown
        }
    }

}