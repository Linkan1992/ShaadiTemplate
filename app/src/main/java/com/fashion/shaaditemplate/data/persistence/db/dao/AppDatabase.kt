package com.fashion.shaaditemplate.data.persistence.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.Profile
import com.fashion.shaaditemplate.data.persistence.db.conveter.Converters

@Database(entities = [Profile::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProfileDao() : ProfileDao

}

