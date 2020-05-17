package com.inkiu.data.mapper

import com.inkiu.data.entities.UserData
import com.inkiu.domain.entities.user.DetailUserEntity

class UserDataToEntityMapper(
    private val dateMapper: UTCStringToDateMapper
) : Mapper<UserData, DetailUserEntity> {

    override fun map(src: UserData): DetailUserEntity {
        return DetailUserEntity(
            id = src.id.toLong(),
            name = src.name,
            displayName = src.screenName,
            profileImageUrl = src.profileImageUrl,
            verified = src.verified,
            location = src.location,
            joinedDate = dateMapper.map(src.createTime),
            followingCount = src.friendCount,
            followerCount = src.followerCount,
            profileUrl = src.url
        )
    }

}