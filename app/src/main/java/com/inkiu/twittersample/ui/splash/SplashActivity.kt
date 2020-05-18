package com.inkiu.twittersample.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.inkiu.twittersample.R
import com.inkiu.twittersample.ui.base.BaseActivity
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var vmFactory: SplashVMFactory
    private val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this, vmFactory)[SplashViewModel::class.java]
    }

    private lateinit var twitterAuthClient: TwitterAuthClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val authConfig = TwitterAuthConfig(
            "lN87u8bySqvkb4WPB1d21ekpW",
            "PhCpSmuyLeXxZgeVpbO34SQOrV2MH8fuUlDvV0KwZ5Tvxgr2ir"
        )

        val twitterConfig = TwitterConfig.Builder(this)
            .twitterAuthConfig(authConfig)
            .build()

        Twitter.initialize(twitterConfig)

        twitterAuthClient = TwitterAuthClient()

        twitterSignButton.setOnClickListener {
            twitterAuthClient.authorize(this, object : Callback<TwitterSession>() {
                override fun success(result: Result<TwitterSession>?) {
                    result?.data?.authToken?.let {
                        viewModel.onTokenArrived(it.token, it.secret)
                    }
                }

                override fun failure(exception: TwitterException?) {
                    toast("트위터 로그인실패")
                }
            })
        }

        viewModel.viewStateData.observe(this, Observer {
            toast(it.isLoggedIn.toString())
            Log.d("test", "${it.isLoggedIn}")
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        twitterAuthClient.onActivityResult(requestCode, resultCode, data)
    }


    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

}