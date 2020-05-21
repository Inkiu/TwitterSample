package com.inkiu.data.mapper

import com.inkiu.data.model.HashTag
import com.inkiu.data.model.Symbol
import com.inkiu.data.model.UserMention
import com.inkiu.domain.entities.tweet.HashTagEntity
import com.inkiu.domain.entities.tweet.SymbolEntity
import com.inkiu.domain.entities.tweet.UserMentionEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HashTagDataToEntityMapper @Inject constructor()  : Mapper<HashTag, HashTagEntity> {

    override fun map(src: HashTag): HashTagEntity {
        return HashTagEntity(
            text = src.text,
            textIndices = src.indices.subList(0, 2)
        )
    }

}

@Singleton
class UserMentionDataToEntityMapper @Inject constructor()  : Mapper<UserMention, UserMentionEntity> {

    override fun map(src: UserMention): UserMentionEntity {
        return UserMentionEntity(
            id = src.id,
            name = src.name,
            screenName = src.screenName,
            textIndices = src.indices.subList(0, 2)
        )
    }

}

@Singleton
class SymbolDataToEntityMapper @Inject constructor() : Mapper<Symbol, SymbolEntity> {

    override fun map(src: Symbol): SymbolEntity {
        return SymbolEntity(
            text = src.text,
            textIndices = src.indices.subList(0, 2)
        )
    }

}