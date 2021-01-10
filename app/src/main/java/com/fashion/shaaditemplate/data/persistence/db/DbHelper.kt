package com.fashion.shaaditemplate.data.persistence.db

import androidx.lifecycle.LiveData
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.Profile

interface DbHelper {

    suspend fun insertProfileListInStore(profileList: List<Profile>)

    fun fetchProfileDataList(): LiveData<List<Profile>>

    suspend fun updateProfileInStore(profileModel: Profile)

    suspend fun clearAllToStore()

}