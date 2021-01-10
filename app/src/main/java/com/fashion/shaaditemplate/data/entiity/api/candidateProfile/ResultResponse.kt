package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ResultResponse(

    @SerializedName("results")
    @Expose
    val results: List<Profile>? = null,

    @SerializedName("info")
    @Expose
    val info: Info? = null

) : Serializable

