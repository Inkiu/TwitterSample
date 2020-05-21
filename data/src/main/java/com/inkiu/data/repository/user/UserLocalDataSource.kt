package com.inkiu.data.repository.user

import com.inkiu.data.model.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor() {
    private val cache = mutableMapOf<Long, UserData>()

    fun updateUsers(users: List<UserData>) {
        users.forEach { cache[it.id] = it }
    }

    fun getUser(index: Long): UserData? {
        return cache[index]
    }

    fun getUsers(indices: List<Long>): List<UserData> {
        return indices.map { userIndex ->
            cache[userIndex] ?: UserData()
        }
    }
}