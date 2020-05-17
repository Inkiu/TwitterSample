package com.inkiu.domain.repositoty

import com.inkiu.domain.entities.user.DetailUserEntity
import com.inkiu.domain.entities.user.UserEntity
import io.reactivex.Single

interface UserRepository {

    fun getUser(id: Long): Single<UserEntity>

    fun getUsers(id: List<Long>): Single<List<UserEntity>>

    fun getDetailUser(id: Long): Single<DetailUserEntity>

}