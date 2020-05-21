package com.inkiu.data.mapper

import com.inkiu.data.model.UserData
import com.inkiu.domain.entities.user.DetailUserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataToEntityMapper @Inject constructor (
    private val dateMapper: UTCStringToDateMapper
) : Mapper<UserData, DetailUserEntity> {

    override fun map(src: UserData): DetailUserEntity {
        return DetailUserEntity(
            id = src.id,
            name = src.screenName,
            displayName = src.name,
            profileImageUrl = src.profileImageUrl.getFullSizeProfileUrl(),
            verified = src.verified,
            location = src.location,
            joinedDate = dateMapper.map(src.createTime),
            followingCount = src.friendCount,
            followerCount = src.followerCount,
            profileUrl = src.url,
            description = src.description
        )
    }

    private fun String.getFullSizeProfileUrl(): String =
        this.replace("_normal", "")
}