package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Street(

    @SerializedName("number")
    @Expose
    var number: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null

) : Serializable

