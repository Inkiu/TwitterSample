package com.inkiu.domain.usecase

import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.domain.repositoty.TweetRepository

class GetReplyTweets(
    private val tweetRepository: TweetRepository
) : SingleUseCase<GetReplyTweets.Param, List<TweetEntity>> {

    /*
    when you fetch the tweet store the tweetId ie., id_str
    using twitter search api do the following query [q="to:$tweeterusername", sinceId = $tweetId]
    Loop all the results , the results matching the in_reply_to_status_id_str to $tweetid is the replies for the post.
     */

    override suspend fun execute(param: Param): List<TweetEntity> {
        val query = "to:${param.userName}"
        var since = param.sinceId
        return tweetRepository.searchTweets(query, since, 100)
    }

    data class Param(
        val userName: String,
        val tweetId: Long,
        val sinceId: Long,
        val count: Int
    )
}