package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Name(

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("first")
    @Expose
    var first: String? = null,

    @SerializedName("last")
    @Expose
    var last: String? = null

) : Serializable

