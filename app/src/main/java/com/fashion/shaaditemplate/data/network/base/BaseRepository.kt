package com.fashion.shaaditemplate.data.network.base


import com.fashion.shaaditemplate.util.constUtil.AppConstants
import retrofit2.Response
import com.fashion.shaaditemplate.util.extension.Result


abstract class BaseRepository {

    protected suspend fun <T : Any> makeApiCall(call: suspend () -> Response<T>, retry : Boolean = false): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            }
            return error(response.code(), response.message())
        } catch (e: Exception) {
            return error(message = e.message ?: e.toString())
        }
    }

    private fun <T : Any> error(errorCode : Int = AppConstants.HttpResCodes.STATUS_NO_INTERNET, message: String): Result<T> {

        return Result.Error(errorCode = errorCode, message = fetchErrorMessage(errorCode = errorCode))
    }


    private fun fetchErrorMessage(errorCode : Int) : String = when(errorCode){
        AppConstants.HttpResCodes.STATUS_NO_INTERNET -> "Please check your internet and try again"
        AppConstants.HttpResCodes.STATUS_NO_ITEMS_FOUND -> "No data found"
        AppConstants.HttpResCodes.STATUS_NOT_FOUND -> "Content Not Found"
        AppConstants.HttpResCodes.STATUS_UNAUTHORIZED -> "Request Unauthorized"
        AppConstants.HttpResCodes.STATUS_SERVER_ERROR -> "Internal Server Error"
        AppConstants.HttpResCodes.STATUS_INTERNAL_ERROR -> "Internal Server Error"
        else -> "Network call has failed due unknown server error"
    }

}
