package com.inkiu.domain.repositoty

import com.inkiu.domain.entities.user.DetailUserEntity
import com.inkiu.domain.entities.user.UserEntity

interface UserRepository {

    fun getUser(id: Long): UserEntity

    fun getUsers(id: List<Long>): List<UserEntity>

    fun getDetailUser(id: Long): DetailUserEntity

}