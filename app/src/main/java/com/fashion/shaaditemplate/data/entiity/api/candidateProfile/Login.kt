package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Login(

    @SerializedName("uuid")
    @Expose
    val uuid: String? = null,

    @SerializedName("username")
    @Expose
    val username: String? = null,

    @SerializedName("password")
    @Expose
    val password: String? = null,

    @SerializedName("salt")
    @Expose
    val salt: String? = null,

    @SerializedName("md5")
    @Expose
    val md5: String? = null,

    @SerializedName("sha1")
    @Expose
    val sha1: String? = null,

    @SerializedName("sha256")
    @Expose
    val sha256: String? = null

) : Serializable

