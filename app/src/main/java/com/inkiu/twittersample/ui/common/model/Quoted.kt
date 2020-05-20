package com.inkiu.twittersample.ui.common.model

import java.util.*

data class Quoted(
    val id: Long,
    val user: User,
    val content: String,
    val createdDate: Date
)