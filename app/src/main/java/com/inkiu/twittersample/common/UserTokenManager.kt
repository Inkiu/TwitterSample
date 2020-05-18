package com.inkiu.twittersample.common

import com.inkiu.data.api.TokenProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // TODO - file 로 남을 수 있게
class UserTokenManager @Inject constructor()
    : TokenProvider {

    private var _token: String = "1"
    private var _tokenSecret: String = "1"
    override val token get() = _token
    override val tokenSecret get() = _tokenSecret

    suspend fun isLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        delay(1000L)
        token != "" && tokenSecret != ""
    }

    suspend fun updateToken(
        token: String,
        secret: String
    ) = withContext(Dispatchers.IO) {
        delay(1000L)
        this@UserTokenManager._token = token
        this@UserTokenManager._tokenSecret = secret
    }

}