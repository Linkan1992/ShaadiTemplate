package com.fashion.shaaditemplate

import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.drawee.backends.pipeline.Fresco
import com.fashion.shaaditemplate.di.component.AppComponent
import com.fashion.shaaditemplate.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class BaseApplication : DaggerApplication() {

    private val appComponent : AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        appComponent.inject(this)
    }

}