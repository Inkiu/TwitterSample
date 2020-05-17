package com.inkiu.domain.entities.tweet

sealed class TextComposeEntity(
    open val text: String,
    open val textIndices: List<Int>
)

data class SymbolEntity(
    override val text: String,
    override val textIndices: List<Int>
) : TextComposeEntity(text, textIndices)

data class HashTagEntity(
    override val text: String,
    override val textIndices: List<Int>
) : TextComposeEntity(text, textIndices)

data class UserMentionEntity(
    val id: Long,
    val name: String,
    val screenName: String,
    override val textIndices: List<Int>
) : TextComposeEntity(name, textIndices)

