package com.inkiu.twittersample.ui.common.model.mapper

import com.inkiu.data.mapper.Mapper
import com.inkiu.domain.entities.user.UserEntity
import com.inkiu.twittersample.ui.common.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserEntityUserMapper @Inject constructor() : Mapper<UserEntity, User> {
    override fun map(src: UserEntity): User {
        return User(
            src.id,
            src.displayName,
            "@${src.name}",
            src.profileImageUrl,
            src.verified
        )
    }
}