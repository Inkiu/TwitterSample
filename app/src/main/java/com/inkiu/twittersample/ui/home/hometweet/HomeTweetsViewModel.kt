package com.inkiu.twittersample.ui.home.hometweet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.domain.usecase.GetUserDetail
import com.inkiu.twittersample.di.PerFragment
import com.inkiu.twittersample.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class HomeTweetsViewModel(
    private val getHomeTweets: GetHomeTweets
) : BaseViewModel() {

    private val viewState = HomeTweetViewState()
    val viewStateData: MutableLiveData<HomeTweetViewState>
            = MutableLiveData(viewState)

    override fun onAttached() {
        launch {
            val result = getHomeTweets.execute(GetHomeTweets.Param(-1L, 100))
            Log.d("homeweetViewmodel", "${result.size}")
        }
    }

    private fun HomeTweetViewState.post() {
        viewStateData.value = this
    }

}

@PerFragment
class HomeTweetsVMFactory @Inject constructor(
    private val getHomeTweets: GetHomeTweets
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeTweetsViewModel(
            getHomeTweets
        ) as T
    }
}