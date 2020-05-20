package com.inkiu.twittersample.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inkiu.domain.usecase.GetTweet
import com.inkiu.twittersample.di.PerActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Named

class DetailViewModel(
) : BaseViewModel() {

}

@PerActivity
class DetailVmFactory @Inject constructor(
    @Named("tweet_id") private val tweetId: Long,
    private val getTweet: GetTweet
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel() as T
    }
}