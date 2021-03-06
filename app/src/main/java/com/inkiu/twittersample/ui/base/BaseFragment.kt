package com.inkiu.twittersample.ui.base

import android.os.Bundle
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().onAttached()
    }

    // TODO get
    abstract fun getViewModel(): BaseViewModel

}