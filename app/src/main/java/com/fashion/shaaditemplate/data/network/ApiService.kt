package com.fashion.shaaditemplate.data.network


import com.fashion.shaaditemplate.util.constUtil.AppConstants
import com.fashion.shaaditemplate.util.requestUtil.RequestKey
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @GET
    suspend fun fetchJobListingDataAsync(
        @Query(RequestKey.ProfileListing.RECORDS_PER_PAGE) recordsPerPage: Int = AppConstants.ITEM_COUNT_PER_PAGE
    ): Response<JSONObject>


}