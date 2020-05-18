package com.inkiu.data

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.inkiu.data.api.TestTwitterApi
import com.inkiu.data.mapper.*
import com.inkiu.data.repository.tweet.TweetLocalDataSource
import com.inkiu.data.repository.tweet.TweetRemoteDataSource
import com.inkiu.data.repository.tweet.TweetRepositoryImpl
import com.inkiu.data.repository.user.UserLocalDataSource
import com.inkiu.data.repository.user.UserRepositoryImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@Suppress("SpellCheckingInspection")
class UserRepositoryTest {

    private lateinit var tweetRemoteDataSource: TweetRemoteDataSource
    private lateinit var tweetLocalDataSource: TweetLocalDataSource
    private lateinit var userLocalDataSource: UserLocalDataSource
    private lateinit var tweetMapper: TweetDataToEntityMapper
    private lateinit var userMapper: UserDataToEntityMapper

    private lateinit var tweetRepositoryImpl: TweetRepositoryImpl
    private lateinit var userRepositoryImpl: UserRepositoryImpl

    @Before
    fun before() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        tweetRemoteDataSource = TweetRemoteDataSource(TestTwitterApi(appContext))
        tweetLocalDataSource = TweetLocalDataSource()
        userLocalDataSource = UserLocalDataSource()

        val dateMapper = UTCStringToDateMapper()
        tweetMapper = TweetDataToEntityMapper(
            dateMapper,
            MediaDataToEntityMapper(),
            HashTagDataToEntityMapper(),
            UserMentionDataToEntityMapper(),
            SymbolDataToEntityMapper()
        )

        tweetRepositoryImpl = TweetRepositoryImpl(
            tweetLocalDataSource,
            tweetRemoteDataSource,
            userLocalDataSource,
            tweetMapper
        )

        userMapper = UserDataToEntityMapper(
            dateMapper
        )

        userRepositoryImpl = UserRepositoryImpl(
            userMapper,
            userLocalDataSource
        )
    }

    @Test
    fun `TweetRepository에서_Tweet을_로딩한_후_user를_찾았을_시_리턴하는지`() {
        // max is 100
        var userIndices: List<Long> = emptyList()
        tweetRepositoryImpl.getHomeTweets(-1L, 100)
            .flatMap {
                userIndices = it.map { it.userIndex }.distinct()
                userRepositoryImpl.getUsers(userIndices)
            }
            .blockingGet()
            .also {
                Assert.assertTrue(it.size == userIndices.size)
                it.forEach {
                    Assert.assertTrue(it.id > 0L)
                }
            }
    }
}
