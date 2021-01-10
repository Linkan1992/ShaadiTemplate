package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Coordinates(

    @SerializedName("latitude")
    @Expose
    val latitude: String? = null,

    @SerializedName("longitude")
    @Expose
    val longitude: String? = null

) : Serializable

