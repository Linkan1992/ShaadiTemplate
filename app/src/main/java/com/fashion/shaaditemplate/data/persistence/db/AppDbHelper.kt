package com.fashion.shaaditemplate.data.persistence.db


import androidx.lifecycle.LiveData
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.Profile
import com.fashion.shaaditemplate.data.persistence.db.dao.AppDatabase
import javax.inject.Inject

class AppDbHelper @Inject constructor(
    private val appDatabase: AppDatabase
) : DbHelper {

    override suspend fun insertProfileListInStore(profileList: List<Profile>) =
        appDatabase.getProfileDao().insertProfileListInStore(profileList)

    override fun fetchProfileDataList(): LiveData<List<Profile>> =
        appDatabase.getProfileDao().fetchProfileList()

    override suspend fun updateProfileInStore(profileModel: Profile) =
        appDatabase.getProfileDao().insertUpdate(profileModel)

    override suspend fun clearAllToStore() =
        appDatabase.getProfileDao().truncateProfileList()


}