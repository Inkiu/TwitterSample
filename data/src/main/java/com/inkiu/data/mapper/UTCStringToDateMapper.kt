package com.inkiu.data.mapper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class UTCStringToDateMapper : Mapper<String, Date> {
    // TODO - date 바꾸는 정확한 방법 알아보기
    // Sun Aug 20 08:52:04 +0000 2017
    private val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US)

    override fun map(src: String): Date {
        return try {
            formatter.parse(src) ?: Date()
        } catch (e: ParseException) {
            Date()
        }
    }
}