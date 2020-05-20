package com.inkiu.twittersample.ui.home.homeprofile

import android.os.Bundle
import android.util.Log
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
import com.inkiu.twittersample.ui.common.tweets.TweetClickListener
import com.inkiu.twittersample.ui.common.tweets.datasource.DataSourceState
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment :
    BaseFragment(),
    TweetClickListener {

    companion object {
        fun newInstance() = ProfileFragment()
    }

//    @Inject
    lateinit var vmFactory: ProfileVMFactory
//    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this, vmFactory)[ProfileViewModel::class.java]
    }

    private val adapter: TweetAdapter by lazy {
        TweetAdapter(imageLoader, this)
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTweetRecyclerView()
        observe()
    }

    private fun initTweetRecyclerView() {
        profileRecycler.adapter = adapter
        profileRecycler.layoutManager = LinearLayoutManager(requireContext())
        profileRecycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun observe() {
        viewModel.pagingListData.observe(this.viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.networkStateData.observe(this.viewLifecycleOwner, Observer {
            adapter.setNetworkState(it)
//            homeTweetRefreshLayout.isRefreshing = it is DataSourceState.LoadingInitial
        })
    }

    override fun onClickTweet(tweetId: Long) {
        Log.d("tmpLog", "onClickTweet: $tweetId")
    }

    override fun onClickUser(userId: Long) {
        Log.d("tmpLog", "onClickUser: $userId")
    }
}