package com.inkiu.domain.usecase

import com.inkiu.domain.entities.user.DetailUserEntity
import com.inkiu.domain.repositoty.UserRepository
import io.reactivex.Single

class GetUserDetail(
    private val userRepository: UserRepository
) : SingleUseCase<GetUserDetail.Param, DetailUserEntity> {

    override fun execute(param: Param): Single<DetailUserEntity> {
        return userRepository.getDetailUser(param.userIndex)
    }

    data class Param(
        val userIndex: Long
    )
}