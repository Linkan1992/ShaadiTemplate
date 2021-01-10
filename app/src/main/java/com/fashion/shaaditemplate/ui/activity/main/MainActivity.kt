package com.fashion.shaaditemplate.ui.activity.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.fashion.shaaditemplate.BR
import com.fashion.shaaditemplate.R
import com.fashion.shaaditemplate.ViewModelProviderFactory
import com.fashion.shaaditemplate.base.BaseActivity
import com.fashion.shaaditemplate.databinding.ActivityMainBinding
import com.fashion.shaaditemplate.ui.fragment.home.HomeFragment
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    companion object {

        fun newIntent(context: Context) {
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val mainViewModel : MainViewModel by lazy {
        ViewModelProvider(this@MainActivity, viewModelProviderFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = mainViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val toolbar: Toolbar?
        get() = null

    override fun permissionGranted(requestCode: Int) {

    }

    override fun initOnCreate(savedInstanceState: Bundle?) {

        loadHomeFrag()
    }

    private fun loadHomeFrag() {
        onFragmentAdd(R.id.fragment_container, HomeFragment.newInstance(), HomeFragment.TAG, R.anim.enter_from_bottom, R.anim.exit_to_bottom)
    }
}
