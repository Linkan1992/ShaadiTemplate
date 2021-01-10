package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Dob(

    @SerializedName("date")
    @Expose
    var date: String? = null,

    @SerializedName("age")
    @Expose
    var age: Int? = null

) : Serializable

