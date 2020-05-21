package com.inkiu.domain.repositoty

import com.inkiu.domain.entities.user.DetailUserEntity
import com.inkiu.domain.entities.user.UserEntity

interface UserRepository {

    suspend fun getUser(id: Long): UserEntity

    suspend fun getUsers(id: List<Long>): List<UserEntity>

    suspend fun getDetailUser(id: Long): DetailUserEntity

    suspend fun getMyProfile(): DetailUserEntity

}