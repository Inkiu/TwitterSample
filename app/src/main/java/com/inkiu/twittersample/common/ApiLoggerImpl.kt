package com.inkiu.twittersample.common

import android.content.Context
import android.util.Log
import com.inkiu.data.api.ApiLogger
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ApiLoggerImpl @Inject constructor(
    @Named("ApplicationContext") private val context : Context
) : ApiLogger {

    // TODO - logging tag
    override fun log(message: String) {
        Log.d("ApiLoggerImpl", message)
    }

}