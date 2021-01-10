package com.fashion.shaaditemplate.data.persistence.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.Profile

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpdate(profileModel: Profile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfileListInStore(profileList: List<Profile>)

    @Query("SELECT * FROM Candidate_Profile")
    fun fetchProfileList() : LiveData<List<Profile>>

    @Query("DELETE FROM Candidate_Profile")
    fun truncateProfileList()

}