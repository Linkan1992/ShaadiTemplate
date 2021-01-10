package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Id(

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("value")
    @Expose
    var value: String? = null

) : Serializable

