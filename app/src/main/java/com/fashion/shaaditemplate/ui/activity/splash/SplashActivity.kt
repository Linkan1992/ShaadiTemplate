package com.fashion.shaaditemplate.ui.activity.splash

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fashion.shaaditemplate.BR
import com.fashion.shaaditemplate.R
import com.fashion.shaaditemplate.ViewModelProviderFactory
import com.fashion.shaaditemplate.base.BaseActivity
import com.fashion.shaaditemplate.databinding.ActivitySplashBinding
import javax.inject.Inject

import com.fashion.shaaditemplate.util.extension.Result



class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {


    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory


    private val splashViewModel: SplashViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory).get(SplashViewModel::class.java)
    }

    override val layoutId: Int
        get() = R.layout.activity_splash


    override val viewModel: SplashViewModel
        get() = splashViewModel


    override val bindingVariable: Int
        get() = BR.viewModel


    override val toolbar: Toolbar?
        get() = null


    override fun initOnCreate(savedInstanceState: Bundle?) {
        subscribeToLiveData()
    }

    override fun permissionGranted(requestCode: Int) {

    }

    private fun subscribeToLiveData() {


        splashViewModel.mStatusLiveData.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> redirectToMainActivity()
                is Result.Error -> {}
            }
        })

    }


    private fun redirectToMainActivity() {
       // MainActivity.newIntent(this@SplashActivity)
        finish()
    }


}

