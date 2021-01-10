package com.fashion.shaaditemplate.di.providerModule

import com.fashion.shaaditemplate.ui.fragment.home.HomeFragModule
import com.fashion.shaaditemplate.ui.fragment.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModuleProvider {

    @ContributesAndroidInjector(modules = [HomeFragModule::class])
    abstract fun provideHomeFragment(): HomeFragment

}