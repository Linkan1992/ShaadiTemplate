package com.fashion.shaaditemplate.data.entiity.api.candidateProfile

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "Candidate_Profile")
data class Profile(

    @PrimaryKey
    @SerializedName("cell")
    @Expose
    var cell: String,

    @SerializedName("gender")
    @Expose
    var gender: String? = null,

    @SerializedName("name")
    @Expose
    var nameModel: Name? = null,

    @SerializedName("location")
    @Expose
    var locationModel: Location? = null,

    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("login")
    @Expose
    @Ignore
    var loginModel: Login? = null,

    @SerializedName("dob")
    @Expose
    var dobModel: Dob? = null,

    @SerializedName("registered")
    @Expose
    var registeredModel: Registered? = null,

    @SerializedName("phone")
    @Expose
    var phone: String? = null,

    @SerializedName("id")
    @Expose
    var idModel: Id? = null,

    @SerializedName("picture")
    @Expose
    var pictureModel: Pictures? = null,

    @SerializedName("nat")
    @Expose
    var nat: String? = null,

    // custom database field
    var acceptanceStatus: String? = null

) : Serializable{

    constructor() : this("")
}