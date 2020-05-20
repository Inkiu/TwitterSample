package com.inkiu.data.repository.user

import com.inkiu.data.api.TwitterApi
import com.inkiu.data.entities.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(
    private val twitterApi: TwitterApi
) {

    suspend fun getMyProfile(): UserData {
        return twitterApi.getMyProfile()
    }

    suspend fun getUserProfile(userIndex: Long): UserData {
        return twitterApi.getUser(userIndex)
    }
}