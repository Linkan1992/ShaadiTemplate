package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Timezone(

    @SerializedName("offset")
    @Expose
    var offset: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null

) : Serializable

