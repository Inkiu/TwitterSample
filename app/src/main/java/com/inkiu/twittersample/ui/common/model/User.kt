package com.inkiu.twittersample.ui.common.model

import java.util.*

// TODO - 묶기
data class User(
    val id: Long,
    val displayName: String,
    val name: String,
    val profileUrl: String,
    val verified: Boolean
)

data class UserDetail(
    val id: Long,
    val displayName: String,
    val name: String,
    val verified: Boolean,

    val location: String,
    val profileUrl: String,
    val joinedDate: Date,
    val followingCount: Int,
    val followerCount: Int
)