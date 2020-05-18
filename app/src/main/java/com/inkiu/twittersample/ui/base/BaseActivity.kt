package com.inkiu.twittersample.ui.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().onAttached()
    }

    abstract fun getViewModel(): BaseViewModel

}