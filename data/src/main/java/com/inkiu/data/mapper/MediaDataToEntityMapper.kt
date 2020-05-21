package com.inkiu.data.mapper

import com.inkiu.data.model.MediaData
import com.inkiu.domain.entities.tweet.MediaEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaDataToEntityMapper @Inject constructor()
    : Mapper<MediaData, MediaEntity> {

    override fun map(src: MediaData): MediaEntity {
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