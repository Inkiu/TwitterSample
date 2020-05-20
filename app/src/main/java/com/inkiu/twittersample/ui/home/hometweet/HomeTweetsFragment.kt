package com.inkiu.twittersample.ui.home.hometweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.inkiu.twittersample.R
import com.inkiu.twittersample.ui.base.BaseFragment
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.tweets.TweetAdapter
import com.inkiu.twittersample.ui.common.tweets.TweetTypeFactory
import com.twitter.sdk.android.core.models.Tweet
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home_tweets.*
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

    private val adapter: TweetAdapter by lazy {
        TweetAdapter(TweetTypeFactory)
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_tweets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tweetRecyclerView.adapter = adapter
        tweetRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.pagingListData.observe(this.viewLifecycleOwner, Observer {
            it.let { adapter.submitList(it) }
        })
    }
}
