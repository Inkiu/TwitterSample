package com.inkiu.data.repository.user

import com.inkiu.data.entities.UserData
import com.inkiu.data.mapper.UserDataToEntityMapper
import com.inkiu.domain.entities.user.DetailUserEntity
import com.inkiu.domain.entities.user.UserEntity
import com.inkiu.domain.repositoty.UserRepository
import io.reactivex.Single

class UserRepositoryImpl(
    private val userDataToEntityMapper: UserDataToEntityMapper,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override fun getUser(id: Long): Single<UserEntity> {
        return getDetailUser(id)
            .cast(UserEntity::class.java)
    }

    override fun getUsers(id: List<Long>): Single<List<UserEntity>> {
        return localDataSource.getUsers(id)
            .map { list ->
                list.map { userDataToEntityMapper(it) }
            }
    }

    override fun getDetailUser(id: Long): Single<DetailUserEntity> {
        return localDataSource.getUser(id)
            .switchIfEmpty(Single.just(UserData()))
            .map { userDataToEntityMapper(it) }
    }

}