package com.inkiu.twittersample.ui.home.hometweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.inkiu.twittersample.R
import com.inkiu.twittersample.ui.base.BaseFragment
import com.inkiu.twittersample.ui.base.BaseViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeTweetsFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeTweetsFragment()
    }

    @Inject
    lateinit var vmFactory: HomeTweetsVMFactory
    private val viewModel: HomeTweetsViewModel by lazy {
        ViewModelProvider(this, vmFactory)[HomeTweetsViewModel::class.java]
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_tweets, container, false)
    }
}
