package com.inkiu.twittersample.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inkiu.twittersample.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity :
    DaggerAppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragmentPager.adapter = HomePagerAdapter(this)
        fragmentPager.isUserInputEnabled = false

        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_bottom_tweet_list -> fragmentPager.currentItem = 0
            R.id.home_bottom_profile -> fragmentPager.currentItem = 1
        }
        return true
    }
}