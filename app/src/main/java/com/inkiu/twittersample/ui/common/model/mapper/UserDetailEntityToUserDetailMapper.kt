package com.inkiu.twittersample.ui.common.model.mapper

import com.inkiu.data.mapper.Mapper
import com.inkiu.domain.entities.user.DetailUserEntity
import com.inkiu.twittersample.ui.common.model.UserDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailEntityToUserDetailMapper @Inject constructor() : Mapper<DetailUserEntity, UserDetail> {
    override fun map(src: DetailUserEntity): UserDetail {
        return UserDetail(
            id = src.id,
            displayName = src.displayName,
            name = "@${src.name}",
            profileUrl = src.profileImageUrl,
            verified = src.verified,

            location = src.location,
            joinedDate = src.joinedDate,
            followingCount = src.followingCount,
            followerCount = src.followerCount
        )
    }
}