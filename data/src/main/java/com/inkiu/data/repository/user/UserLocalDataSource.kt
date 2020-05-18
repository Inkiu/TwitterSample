package com.inkiu.data.repository.user

import com.inkiu.data.entities.UserData
import java.nio.file.attribute.UserDefinedFileAttributeView

class UserLocalDataSource {
    private val cache = mutableMapOf<Long, UserData>()

    fun updateUsers(users: List<UserData>) {
        users.forEach { cache[it.id] = it }
    }

    fun getUser(index: Long): UserData {
        return cache[index] ?: UserData()
    }

    fun getUsers(indices: List<Long>): List<UserData> {
        return indices.map { userIndex ->
            cache[userIndex] ?: UserData()
        }
    }
}