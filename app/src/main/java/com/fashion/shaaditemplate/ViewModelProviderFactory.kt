package com.fashion.shaaditemplate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fashion.shaaditemplate.data.network.ApiHelper
import com.fashion.shaaditemplate.data.persistence.db.DbHelper
import com.fashion.shaaditemplate.di.annotation.CoroutineScopeIO
import com.fashion.shaaditemplate.ui.activity.main.MainViewModel
import com.fashion.shaaditemplate.ui.activity.splash.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject
constructor(
    private val dbHelper: DbHelper,
    private val apiHelper: ApiHelper,
    @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(ioCoroutineScope) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(dbHelper, apiHelper, ioCoroutineScope) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name} " /*+ modelClass.name*/)
        }
    }
}