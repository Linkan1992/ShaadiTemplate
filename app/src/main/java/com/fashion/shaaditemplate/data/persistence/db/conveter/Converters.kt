package com.fashion.shaaditemplate.data.persistence.db.conveter

import androidx.room.TypeConverter
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.*
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object Converters {

    @TypeConverter
    @JvmStatic
    fun fromNameString(value: String?): Name? {
        val nameModel: Type = object : TypeToken<Name?>() {}.type
        return Gson().fromJson(value, nameModel)
    }

    @TypeConverter
    @JvmStatic
    fun fromNameModel(nameModel: Name?): String? {
        val gson = Gson()
        return gson.toJson(nameModel)
    }


    @TypeConverter
    @JvmStatic
    fun fromLocationString(value: String?): Location? {
        val locationModel: Type = object : TypeToken<Location?>() {}.type
        return Gson().fromJson(value, locationModel)
    }

    @TypeConverter
    @JvmStatic
    fun fromLocationModel(locationModel: Location?): String? {
        val gson = Gson()
        return gson.toJson(locationModel)
    }


    @TypeConverter
    @JvmStatic
    fun fromDobString(value: String?): Dob? {
        val model: Type = object : TypeToken<Dob?>() {}.type
        return Gson().fromJson(value, model)
    }

    @TypeConverter
    @JvmStatic
    fun fromDobModel(model: Dob?): String? {
        val gson = Gson()
        return gson.toJson(model)
    }


    @TypeConverter
    @JvmStatic
    fun fromRegisteredString(value: String?): Registered? {
        val model: Type = object : TypeToken<Registered?>() {}.type
        return Gson().fromJson(value, model)
    }

    @TypeConverter
    @JvmStatic
    fun fromRegisteredModel(model: Registered?): String? {
        val gson = Gson()
        return gson.toJson(model)
    }


    @TypeConverter
    @JvmStatic
    fun fromIdString(value: String?): Id? {
        val model: Type = object : TypeToken<Id?>() {}.type
        return Gson().fromJson(value, model)
    }

    @TypeConverter
    @JvmStatic
    fun fromIdModel(model: Id?): String? {
        val gson = Gson()
        return gson.toJson(model)
    }


    @TypeConverter
    @JvmStatic
    fun fromPictureString(value: String?): Pictures? {
        val model: Type = object : TypeToken<Pictures?>() {}.type
        return Gson().fromJson(value, model)
    }

    @TypeConverter
    @JvmStatic
    fun fromPictureModel(model: Pictures?): String? {
        val gson = Gson()
        return gson.toJson(model)
    }

}