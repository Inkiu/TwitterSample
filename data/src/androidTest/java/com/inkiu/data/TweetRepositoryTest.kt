

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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@Suppress("SpellCheckingInspection")
class TweetRepositoryTest {

    private lateinit var tweetRemoteDataSource: TweetRemoteDataSource
    private lateinit var tweetLocalDataSource: TweetLocalDataSource
    private lateinit var userLocalDataSource: UserLocalDataSource
    private lateinit var mapper: TweetDataToEntityMapper

    private lateinit var tweetRepositoryImpl: TweetRepositoryImpl

    @Before
    fun before() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        tweetRemoteDataSource = TweetRemoteDataSource(TestTwitterApi(appContext))
        tweetLocalDataSource = TweetLocalDataSource()
        userLocalDataSource = UserLocalDataSource()

        val dateMapper = UTCStringToDateMapper()
        mapper = TweetDataToEntityMapper(
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
            mapper
        )
    }

    @Test
    fun `TweetRepository가_homeTweets을_요청했을_시_count에_맞게_리턴한다`() {
        // max is 100
        tweetRepositoryImpl.getHomeTweets(-1L, 50)
            .blockingGet()
            .also {
                assertTrue(it.size == 50)
                it.forEach {
                    assertTrue(it.id > 0L)
                }
            }

        tweetRepositoryImpl.getHomeTweets(-1L, 100)
            .blockingGet()
            .also {
                assertTrue(it.size == 100)
                it.forEach {
                    assertTrue(it.id > 0L)
                }
            }
    }
}
