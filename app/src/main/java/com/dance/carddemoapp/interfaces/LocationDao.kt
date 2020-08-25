package com.dance.carddemoapp.interfaces

import androidx.room.*
import com.dance.carddemoapp.models.LocationModel

/**
 * @author:Shivani Bajpai
 * @description: Dao class for Location Model
 */
@Dao
interface LocationDao {
    @Insert
    fun addLocData(mydatalist: LocationModel?)

    @Query("select * from locationmodel")
    fun getLocData(): List<LocationModel?>?

    @Delete
    fun deleteLocData(mydatalist: LocationModel?)

    @Update
    fun updateLocData(mydatalist: LocationModel?)
}