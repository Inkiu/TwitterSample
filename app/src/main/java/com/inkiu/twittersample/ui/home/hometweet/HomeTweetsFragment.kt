package com.inkiu.twittersample.ui.home.hometweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.inkiu.twittersample.R
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.ui.base.BaseFragment
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.common.tweets.TweetAdapter
import kotlinx.android.synthetic.main.fragment_home_tweets.*
import javax.inject.Inject

class HomeTweetsFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeTweetsFragment()
    }

    @Inject
    lateinit var vmFactory: HomeTweetsVMFactory
    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel: HomeTweetsViewModel by lazy {
        ViewModelProvider(this, vmFactory)[HomeTweetsViewModel::class.java]
    }

    private val adapter: TweetAdapter by lazy {
        TweetAdapter(imageLoader)
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
        initTweetRecyclerView()
        observe()
    }

    private fun initTweetRecyclerView() {
        tweetRecyclerView.adapter = adapter
        tweetRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        tweetRecyclerView.addItemDecoration(DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL)
        )
    }

    private fun observe() {
        viewModel.pagingListData.observe(this.viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.networkStateData.observe(this.viewLifecycleOwner, Observer {
            adapter.setNetworkState(it)
        })
    }
}
