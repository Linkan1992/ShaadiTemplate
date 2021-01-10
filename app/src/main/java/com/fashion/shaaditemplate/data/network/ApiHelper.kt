package com.fashion.shaaditemplate.data.network

import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.ResultResponse
import com.fashion.shaaditemplate.util.extension.Result

interface ApiHelper {

     suspend fun fetchProfileDataList() : Result<ResultResponse>

}