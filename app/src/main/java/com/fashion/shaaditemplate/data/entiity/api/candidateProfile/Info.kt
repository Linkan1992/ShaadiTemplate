package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Info(

    @SerializedName("seed")
    @Expose
    val seed: String? = null,

    @SerializedName("results")
    @Expose
    val results: Int? = null,

    @SerializedName("page")
    @Expose
    val page: Int? = null,

    @SerializedName("version")
    @Expose
    val version: String? = null

) : Serializable

