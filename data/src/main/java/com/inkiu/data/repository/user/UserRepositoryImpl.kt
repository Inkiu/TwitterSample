package com.inkiu.data.repository.user

import com.inkiu.data.entities.UserData
import com.inkiu.data.mapper.UserDataToEntityMapper
import com.inkiu.domain.entities.user.DetailUserEntity
import com.inkiu.domain.entities.user.UserEntity
import com.inkiu.domain.repositoty.UserRepository

class UserRepositoryImpl(
    private val userDataToEntityMapper: UserDataToEntityMapper,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun getUser(id: Long): UserEntity {
        return getDetailUser(id)
    }

    override suspend fun getUsers(id: List<Long>): List<UserEntity> {
        return localDataSource.getUsers(id)
            .map { userDataToEntityMapper(it) }
    }

    override suspend fun getDetailUser(id: Long): DetailUserEntity {
        return userDataToEntityMapper(localDataSource.getUser(id))
    }

}