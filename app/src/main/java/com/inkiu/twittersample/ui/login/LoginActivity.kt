package com.inkiu.twittersample.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.inkiu.twittersample.R
import com.inkiu.twittersample.ui.base.BaseActivity
import com.inkiu.twittersample.ui.base.BaseViewModel
import com.inkiu.twittersample.ui.home.HomeActivity
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    @Inject
    lateinit var vmFactory: LoginVMFactory
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, vmFactory)[LoginViewModel::class.java]
    }

    private val twitterAuthClient = TwitterAuthClient()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        observe()
        twitterSignButton.setOnClickListener {
            loginTwitter()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        twitterAuthClient.onActivityResult(requestCode, resultCode, data)
    }

    private fun observe() {
        viewModel.viewStateData.observe(this, Observer {
            when (it.loginState) {
                LoginState.LOGIN_SUCCESS -> navigateHomeActivity()
                LoginState.LOGIN_FAILED -> toast("failed")
            }
        })
    }

    private fun navigateHomeActivity() {
        // TODO - finish? or flag and transition
//        startActivity(
//            HomeActivity.newIntent(this).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//        )
        startActivity(HomeActivity.newIntent(this))
        finish()
    }

    private fun loginTwitter() {
        twitterAuthClient.authorize(this, object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                result?.data?.authToken?.let {
                    viewModel.onTokenArrived(it.token, it.secret)
                }
            }

            override fun failure(exception: TwitterException?) {
            }
        })
    }
}