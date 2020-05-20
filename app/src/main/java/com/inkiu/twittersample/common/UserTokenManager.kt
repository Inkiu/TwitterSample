package com.inkiu.twittersample.common

import android.content.Context
import android.content.SharedPreferences
import com.inkiu.data.api.TokenProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton // TODO - file 로 남을 수 있게
class UserTokenManager @Inject constructor(
    @Named("ApplicationContext") context: Context
) : TokenProvider {

    companion object {
        const val PREF_FILE_NAME = "pref_token_store"
        const val FIELD_TOKEN = "pref_field_token"
        const val FIELD_SECRET = "pref_field_secret"
    }

    private val tokenStore: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    // default value is not null
    override val token: String get() = tokenStore.getString(FIELD_TOKEN, "")!!
    override val tokenSecret: String get() = tokenStore.getString(FIELD_SECRET, "")!!

    suspend fun isLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        token.isNotEmpty() && tokenSecret.isNotEmpty()
    }

    suspend fun updateToken(
        token: String,
        secret: String
    ) = withContext(Dispatchers.IO) {
        tokenStore.edit().apply {
            putString(FIELD_TOKEN, token)
            putString(FIELD_SECRET, secret)
        }.apply()
    }

}