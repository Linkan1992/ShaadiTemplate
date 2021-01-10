package com.fashion.shaaditemplate.ui.fragment.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.fashion.shaaditemplate.ui.adapter.profileAdapter.CandidateProfileAdapter
import dagger.Module
import dagger.Provides

@Module
class HomeFragModule {

    @Provides
    fun provideProfileAdapter(fragment: HomeFragment) : CandidateProfileAdapter = CandidateProfileAdapter(ArrayList(), fragment)

    @Provides
    fun provideLayoutManager(fragment: HomeFragment) : LinearLayoutManager = LinearLayoutManager(fragment.context)

}