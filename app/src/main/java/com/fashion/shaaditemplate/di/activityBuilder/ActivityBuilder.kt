package com.fashion.shaaditemplate.di.activityBuilder

import com.fashion.shaaditemplate.ui.activity.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun provideSplashActivity(): SplashActivity

    /*@ContributesAndroidInjector(
        modules = [
            MainModule::class
        ]
    )
    abstract fun provideMainActivity(): MainActivity*/

}