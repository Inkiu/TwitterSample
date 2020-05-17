package com.inkiu.data.mapper

import com.inkiu.data.entities.entities.HashTag
import com.inkiu.data.entities.entities.Symbol
import com.inkiu.data.entities.entities.UserMention
import com.inkiu.domain.entities.tweet.HashTagEntity
import com.inkiu.domain.entities.tweet.SymbolEntity
import com.inkiu.domain.entities.tweet.UserMentionEntity

class HashTagDataToEntityMapper : Mapper<HashTag, HashTagEntity> {

    override fun map(src: HashTag): HashTagEntity {
        return HashTagEntity(
            text = src.text,
            textIndices = src.indices.subList(0, 2)
        )
    }

}

class UserMentionDataToEntityMapper : Mapper<UserMention, UserMentionEntity> {

    override fun map(src: UserMention): UserMentionEntity {
        return UserMentionEntity(
            id = src.id,
            name = src.name,
            screenName = src.screenName,
            textIndices = src.indices.subList(0, 2)
        )
    }

}

class SymbolDataToEntityMapper : Mapper<Symbol, SymbolEntity> {

    override fun map(src: Symbol): SymbolEntity {
        return SymbolEntity(
            text = src.text,
            textIndices = src.indices.subList(0, 2)
        )
    }

}