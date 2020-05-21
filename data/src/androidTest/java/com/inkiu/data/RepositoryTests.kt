package com.inkiu.data

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.inkiu.data.api.TestTwitterApi
import com.inkiu.data.mapper.*
import com.inkiu.data.repository.tweet.TweetLocalDataSource
import com.inkiu.data.repository.tweet.TweetRemoteDataSource
import com.inkiu.data.repository.tweet.TweetRepositoryImpl
import com.inkiu.data.repository.user.UserLocalDataSource
import com.inkiu.data.repository.user.UserRemoteDataSource
import com.inkiu.data.repository.user.UserRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4ClassRunner::class)
@Suppress("SpellCheckingInspection")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RepositoryTests {

    private lateinit var tweetRemoteDataSource: TweetRemoteDataSource
    private lateinit var tweetLocalDataSource: TweetLocalDataSource
    private lateinit var userLocalDataSource: UserLocalDataSource
    private lateinit var userRemoteDataSource: UserRemoteDataSource
    private lateinit var tweetMapper: TweetDataToEntityMapper
    private lateinit var userMapper: UserDataToEntityMapper

    private lateinit var tweetRepositoryImpl: TweetRepositoryImpl
    private lateinit var userRepositoryImpl: UserRepositoryImpl

    @Before
    fun before() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // Note - TestTwitterApi의 최대 카운트는 100
        val api = TestTwitterApi(appContext)
        tweetRemoteDataSource = TweetRemoteDataSource(api)
        tweetLocalDataSource = TweetLocalDataSource()
        userLocalDataSource = UserLocalDataSource()
        userRemoteDataSource = UserRemoteDataSource(api)

        val dateMapper = UTCStringToDateMapper()

        userMapper = UserDataToEntityMapper(
            dateMapper
        )

        tweetMapper = TweetDataToEntityMapper(
            dateMapper,
            userMapper,
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


        userRepositoryImpl = UserRepositoryImpl(
            userMapper,
            userLocalDataSource,
            userRemoteDataSource
        )
    }

    @Test // TweetRepository가_homeTweets을_요청했을_시_count에_맞게_리턴한다
    fun test01() = runBlocking {
        val homeTweets50 = tweetRepositoryImpl.getHomeTweets(-1L, 50)
        assertEquals(homeTweets50.size, 50)
        homeTweets50.forEach { assertTrue(it.id > 0L) }

        val homeTweets100 = tweetRepositoryImpl.getHomeTweets(-1L, 100)
        assertEquals(homeTweets100.size, 100)
        homeTweets100.forEach { assertTrue(it.id > 0L) }
    }

    @Test // TweetRepository가_userTweets을_요청했을_시_count에_맞게_리턴한다
    fun test02() = runBlocking {
        val userTweets50 = tweetRepositoryImpl.getUserTweets(0L, -1L, 50)
        assertEquals(userTweets50.size, 50)
        userTweets50.forEach { assertTrue(it.id > 0L) }

        val userTweets100 = tweetRepositoryImpl.getUserTweets(0L, -1L, 100)
        assertEquals(userTweets100.size, 100)
        userTweets100.forEach { assertTrue(it.id > 0L) }
    }

    @Test // TweetRepository에서_Tweet을_로딩한_후_user를_찾았을_시_리턴한다
    fun test03() = runBlocking {
        val tweets = tweetRepositoryImpl.getHomeTweets(-1L, 100)
        val userIndices = tweets.map { it.userEntity.id }.distinct()
        val users = userRepositoryImpl.getUsers(userIndices)

        assertTrue(users.size == userIndices.size)
        users.forEach { assertTrue(it.id > 0L) }
    }
}
