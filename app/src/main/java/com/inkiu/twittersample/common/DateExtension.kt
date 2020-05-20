package com.inkiu.twittersample.common

import android.text.format.DateUtils
import java.util.*

fun Date.relatedTimeString(): String {
    return DateUtils.getRelativeTimeSpanString(
        time,
        System.currentTimeMillis(),
        DateUtils.SECOND_IN_MILLIS
    ).toString()
}