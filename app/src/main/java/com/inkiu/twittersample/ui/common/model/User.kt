package com.inkiu.twittersample.ui.common.model

data class User(
    val id: Long,
    val displayName: String,
    val name: String,
    val profileUrl: String,
    val verified: Boolean
)