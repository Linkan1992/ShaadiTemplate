package com.fashion.shaaditemplate.data.persistence.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fashion.shaaditemplate.data.entiity.db.FavouritePairStore
import com.fashion.shaaditemplate.data.entiity.db.ProductStore

@Database(entities = [ProductStore::class, FavouritePairStore::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductStoreDao() : ProductStoreDao

    abstract fun getFavPairStoreDao() : FavouritePairDao

}