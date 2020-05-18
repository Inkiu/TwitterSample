package com.inkiu.domain.usecase

import com.inkiu.domain.entities.user.DetailUserEntity
import com.inkiu.domain.repositoty.UserRepository

class GetUserDetail(
    private val userRepository: UserRepository
) : SingleUseCase<GetUserDetail.Param, DetailUserEntity> {

    override suspend fun execute(param: Param): DetailUserEntity {
        return userRepository.getDetailUser(param.userIndex)
    }

    data class Param(
        val userIndex: Long
    )
}