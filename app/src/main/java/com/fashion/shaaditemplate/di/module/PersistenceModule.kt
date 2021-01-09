package com.fashion.shaaditemplate.di.module


import android.app.Application
import androidx.room.Room
import com.fashion.shaaditemplate.data.persistence.db.dao.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule{

    @Provides
    @Singleton
    internal fun provideAppDatabase(application : Application) : AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "Shaadi_Store.db")
            .fallbackToDestructiveMigration()
            .build()
    }

}