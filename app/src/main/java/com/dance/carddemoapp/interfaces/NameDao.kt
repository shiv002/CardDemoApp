package com.dance.carddemoapp.interfaces

import androidx.room.*
import com.dance.carddemoapp.models.NameModel

/**
 * @author:Shivani Bajpai
 * @description: Dao class for Name Model
 */
@Dao
interface NameDao {
    @Insert
    fun addNameData(mydatalist: NameModel?)

    @Query("select * from namemodel")
    fun getNameData(): List<NameModel?>?

    @Delete
    fun deleteName(mydatalist: NameModel?)

    @Update
    fun updateName(mydatalist: NameModel?)
}