package com.inkiu.data.repository.user

import com.inkiu.data.entities.UserData
import com.inkiu.data.mapper.UserDataToEntityMapper
import com.inkiu.domain.entities.user.DetailUserEntity
import com.inkiu.domain.entities.user.UserEntity
import com.inkiu.domain.repositoty.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDataToEntityMapper: UserDataToEntityMapper,
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    private var myProfileId: Long = -1L // TODO - Constant

    override suspend fun getUser(id: Long): UserEntity {
        return getDetailUser(id)
    }

    override suspend fun getUsers(id: List<Long>): List<UserEntity> {
        return id.map { getDetailUser(it) }
    }

    override suspend fun getDetailUser(id: Long): DetailUserEntity {
        return getUserLocal(id) ?: getUserRemote(id)
    }

    override suspend fun getMyProfile(): DetailUserEntity {
        return if (myProfileId != -1L) { // TODO - Constant
            getDetailUser(myProfileId)
        } else {
            remoteDataSource.getMyProfile().also {
                localDataSource.updateUsers(listOf(it))
            }.let {
                userDataToEntityMapper(it)
            }
        }
    }

    private suspend fun getUserLocal(id: Long): DetailUserEntity? {
        return localDataSource.getUser(id)?.let {
            userDataToEntityMapper(it)
        }
    }

    private suspend fun getUserRemote(id: Long): DetailUserEntity {
        return remoteDataSource.getUserProfile(id).also {
            localDataSource.updateUsers(listOf(it))
        }.let {
            userDataToEntityMapper(it)
        }
    }
}