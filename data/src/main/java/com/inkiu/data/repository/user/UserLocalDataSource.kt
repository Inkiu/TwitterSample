package com.inkiu.data.repository.user

import com.inkiu.data.entities.UserData
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.nio.file.attribute.UserDefinedFileAttributeView

class UserLocalDataSource {
    private val cache = mutableMapOf<Long, UserData>()

    fun updateUsers(users: List<UserData>): Completable {
        return Completable.fromCallable {
            users.forEach { cache[it.id.toLong()] = it }
        }
    }

    fun getUser(index: Long): Maybe<UserData> {
        return Maybe.fromCallable { cache[index] }
    }

    fun getUsers(indices: List<Long>): Single<List<UserData>> {
        return Single.fromCallable {
            indices.map { userIndex ->
                cache[userIndex] ?: UserData()
            }
        }
    }
}