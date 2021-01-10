package com.fashion.shaaditemplate.data.network


import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.ResultResponse
import com.google.gson.Gson
import com.fashion.shaaditemplate.data.network.base.BaseRepository
import com.fashion.shaaditemplate.util.extension.Result
import javax.inject.Inject


class AppApiHelper @Inject constructor(
    private val apiService: ApiService,
    private val gson : Gson
) : BaseRepository(), ApiHelper {

    override suspend fun fetchProfileDataList(): Result<ResultResponse> =
        makeApiCall(call = { apiService.fetchJobListingDataAsync() })


}