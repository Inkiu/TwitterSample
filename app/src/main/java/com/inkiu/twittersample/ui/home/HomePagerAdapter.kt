package com.inkiu.twittersample.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.inkiu.twittersample.ui.home.homeprofile.ProfileFragment
import com.inkiu.twittersample.ui.home.hometweet.HomeTweetsFragment

class HomePagerAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeTweetsFragment.newInstance()
            1 -> ProfileFragment.newInstance()
            else -> throw IllegalArgumentException()
        }
    }
}