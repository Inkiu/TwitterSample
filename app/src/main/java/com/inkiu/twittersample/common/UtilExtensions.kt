package com.inkiu.twittersample.common

import android.text.format.DateUtils
import android.view.View
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

fun Date.relatedTimeString(): String {
    return DateUtils.getRelativeTimeSpanString(
        time,
        System.currentTimeMillis(),
        DateUtils.SECOND_IN_MILLIS
    ).toString()
}

fun Int.getDecimalSize(): String {
    if (this < 1000) return "" + this
    val exp = (ln(this.toDouble()) / ln(1000.0)).toInt()
    return String.format("%.1f %c", this / 1000.0.pow(exp.toDouble()), "KMGTPE"[exp - 1])
}

fun Boolean.toVisibility(invisible: Boolean = false)
    = if (this) View.VISIBLE else if (invisible) View.VISIBLE else View.GONE