package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Location(

    @SerializedName("street")
    @Expose
    var streetModel: Street? = null,

    @SerializedName("city")
    @Expose
    var city: String? = null,

    @SerializedName("state")
    @Expose
    var state: String? = null,

    @SerializedName("country")
    @Expose
    var country: String? = null,

    @SerializedName("postcode")
    @Expose
    var postcode: String? = null,

    @SerializedName("coordinates")
    @Expose
    var coordinates: Coordinates? = null,

    @SerializedName("timezone")
    @Expose
    var timezoneModel: Timezone? = null

) : Serializable

