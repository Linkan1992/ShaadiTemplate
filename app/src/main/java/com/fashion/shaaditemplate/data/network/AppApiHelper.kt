package com.fashion.shaaditemplate.data.network


import com.google.gson.Gson
import com.fashion.shaaditemplate.data.network.base.BaseRepository
import javax.inject.Inject


class AppApiHelper @Inject constructor(
    private val apiService: ApiService,
    private val gson : Gson
) : BaseRepository(), ApiHelper {


}