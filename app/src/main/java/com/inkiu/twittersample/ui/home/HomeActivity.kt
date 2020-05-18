package com.inkiu.twittersample.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inkiu.twittersample.R
import com.inkiu.twittersample.ui.base.BaseActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.base.EmptyViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity :
    AppCompatActivity(),
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