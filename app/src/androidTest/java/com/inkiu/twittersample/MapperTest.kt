package com.inkiu.twittersample

import android.content.Context
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.inkiu.data.api.TestTwitterApi
import com.inkiu.data.mapper.*
import com.inkiu.data.repository.tweet.TweetLocalDataSource
import com.inkiu.data.repository.tweet.TweetRemoteDataSource
import com.inkiu.data.repository.tweet.TweetRepositoryImpl
import com.inkiu.data.repository.user.UserLocalDataSource
import com.inkiu.data.repository.user.UserRepositoryImpl
import com.inkiu.domain.Constant
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.domain.usecase.GetUserTweets
import com.inkiu.twittersample.model.mapper.*
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
class MapperTest {

    private lateinit var userRepository: UserRepository
    private lateinit var tweetRepository: TweetRepository

    private lateinit var getHomeTweets: GetHomeTweets
    private lateinit var getUserTweets: GetUserTweets

    private lateinit var tweetMapper: TweetEntityTweetMapper

    @Before
    fun before() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        initRepositorys(appContext)
        initUseCases(appContext)

        val userEntityMapper = UserEntityUserMapper()
        tweetMapper = TweetEntityTweetMapper(
            userEntityMapper,
            TweetEntityQuotedMapper(userEntityMapper),
            TextComposeToSpannableMapper(),
            MediaEntityMediaMapper()
        )
    }

    private fun initRepositorys(context: Context) {
        val tweetRemoteDataSource = TweetRemoteDataSource(TestTwitterApi(context))
        val tweetLocalDataSource = TweetLocalDataSource()
        val userLocalDataSource = UserLocalDataSource()

        val dateMapper = UTCStringToDateMapper()
        val userMapper = UserDataToEntityMapper(dateMapper)
        val tweetMapper = TweetDataToEntityMapper(
            dateMapper,
            userMapper,
            MediaDataToEntityMapper(),
            HashTagDataToEntityMapper(),
            UserMentionDataToEntityMapper(),
            SymbolDataToEntityMapper()
        )
        tweetRepository = TweetRepositoryImpl(
            tweetLocalDataSource,
            tweetRemoteDataSource,
            userLocalDataSource,
            tweetMapper
        )
        userRepository = UserRepositoryImpl(
            userMapper,
            userLocalDataSource
        )
    }

    private fun initUseCases(context: Context) {
        getHomeTweets = GetHomeTweets(userRepository, tweetRepository)
        getUserTweets = GetUserTweets(userRepository, tweetRepository)
    }

    @Test // TweetEntity에서 Tweet이 매핑된다
    fun test01() = runBlocking {
        val entities = getHomeTweets.execute(GetHomeTweets.Param(Constant.INVALID_ID, 100))
        val models = entities.map { tweetMapper.map(it) }
        assertTrue(entities.size == models.size)
        assertTrue(models.all { it.id > 0 })
        assertTrue(models.filter { it.isQuotedContained() }.all { it.quoted!!.id > 0 })
    }
}
