package com.inkiu.data.api.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.*

class RemainCallLoggingInterceptor(
    val logger: (msg: String) -> Unit
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).also { response ->
            val remainCall = response.header("x-rate-limit-remaining")
            val until = response.header("x-rate-limit-reset")?.let {
                SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(Date(it.toLong() * 1000L))
            }
            logger("remain $remainCall calls until $until")
        }
    }
}