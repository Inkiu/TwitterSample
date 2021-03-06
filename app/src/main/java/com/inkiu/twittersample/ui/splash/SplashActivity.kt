package com.inkiu.twittersample.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.inkiu.twittersample.R
import com.inkiu.twittersample.ui.base.BaseActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.home.HomeActivity
import com.inkiu.twittersample.ui.login.LoginActivity
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var vmFactory: SplashVMFactory
    private val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this, vmFactory)[SplashViewModel::class.java]
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        observe()
    }

    private fun observe() {
         viewModel.viewStateData.observe(this, Observer {
             when (it.loginState) {
                 LoginState.LOGGED_OUT -> navigateLoginActivity()
                 LoginState.LOGGED_IN -> navigateHomeActivity()
             }
         })
    }

    private fun navigateLoginActivity() {
        startActivity(LoginActivity.newIntent(this))
    }

    private fun navigateHomeActivity() {
        startActivity(HomeActivity.newIntent(this))
    }

}