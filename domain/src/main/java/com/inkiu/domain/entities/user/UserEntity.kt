package com.inkiu.domain.entities.user

import java.util.*

sealed class UserEntity(
    open val id: Long,
    open val name: String,
    open val displayName: String,
    open val profileImageUrl: String,
    open val verified: Boolean
)

data class SimpleUserEntity(
    override val id: Long,
    override val name: String,
    override val displayName: String,
    override val profileImageUrl: String,
    override val verified: Boolean
) : UserEntity(id, name, displayName, profileImageUrl, verified)

data class DetailUserEntity(
    override val id: Long,
    override val name: String,
    override val displayName: String,
    override val profileImageUrl: String,
    override val verified: Boolean,

    val location: String,
    val profileUrl: String,
    val joinedDate: Date,
    val followingCount: Int,
    val followerCount: Int
) : UserEntity(id, name, displayName, profileImageUrl, verified)