package com.fashion.shaaditemplate.data.persistence.db


import androidx.lifecycle.LiveData
import com.fashion.shaaditemplate.data.persistence.db.dao.AppDatabase
import javax.inject.Inject

class AppDbHelper @Inject constructor(
    private val appDatabase: AppDatabase
) : DbHelper {


}