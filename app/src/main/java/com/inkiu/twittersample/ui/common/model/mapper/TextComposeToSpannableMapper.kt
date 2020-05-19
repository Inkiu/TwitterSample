package com.inkiu.twittersample.ui.common.model.mapper

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt
import com.inkiu.data.mapper.Mapper
import com.inkiu.domain.entities.tweet.HashTagEntity
import com.inkiu.domain.entities.tweet.SymbolEntity
import com.inkiu.domain.entities.tweet.TextComposeEntity
import com.inkiu.domain.entities.tweet.UserMentionEntity

class TextComposeToSpannableMapper : Mapper<Pair<String, List<TextComposeEntity>>, Spannable> {

    override fun map(src: Pair<String, List<TextComposeEntity>>): Spannable {
        val spannableString = SpannableString(src.first)
        src.second.filter {
            it.textIndices.size >= 2 &&
                    it.textIndices.max() ?: Int.MAX_VALUE < spannableString.length
        }.forEach {
            val color = when (it) {
                is SymbolEntity -> Color.BLUE
                is HashTagEntity -> Color.CYAN
                is UserMentionEntity -> Color.MAGENTA
                else -> Color.BLACK
            }
            setSpan(spannableString, it.textIndices[0], it.textIndices[1], color)
        }
        return spannableString
    }

    private fun setSpan(
        text: SpannableString,
        from: Int,
        to: Int,
        @ColorInt color: Int
    ): Spannable {
        return text.apply {
            setSpan(
                ForegroundColorSpan(color),
                from, to,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

}