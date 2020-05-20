package com.inkiu.domain.usecase

import com.inkiu.domain.entities.user.UserEntity
import com.inkiu.domain.repositoty.UserRepository

class GetUser(
    private val userRepository: UserRepository
) : SingleUseCase<GetUser.Param, UserEntity> {

    override suspend fun execute(param: Param): UserEntity {
        return when (param) {
            is Param.User -> userRepository.getDetailUser(param.id)
            else -> userRepository.getMyProfile()
        }
    }

    sealed class Param {
        data class User(val id: Long) : Param()
        object MyProfile: Param()
    }
}