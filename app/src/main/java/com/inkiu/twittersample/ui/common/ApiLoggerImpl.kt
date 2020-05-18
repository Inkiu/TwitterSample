package com.inkiu.twittersample.ui.common

import android.content.Context
import android.util.Log
import com.inkiu.data.api.ApiLogger
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ApiLoggerImpl(
    @Named("ApplicationContext") private val context : Context
) : ApiLogger {

    // TODO - logging tag
    override fun log(message: String) {
        Log.d("ApiLoggerImpl", message)
    }

}