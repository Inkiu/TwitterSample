package com.inkiu.twittersample.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().onAttached()
    }

    abstract fun getViewModel(): BaseViewModel

    fun BaseActivity.toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

}