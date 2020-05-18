package com.inkiu.twittersample.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // TODO - file 로 남을 수 있게
class UserTokenManager @Inject constructor() {

    var token: String = ""
    var tokenSecret: String = ""

    suspend fun isLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        delay(1000L)
        token != "" && tokenSecret != ""
    }

    suspend fun updateToken(
        token: String,
        secret: String
    ) = withContext(Dispatchers.IO) {
        delay(1000L)
        this@UserTokenManager.token = token
        this@UserTokenManager.tokenSecret = secret
    }

}