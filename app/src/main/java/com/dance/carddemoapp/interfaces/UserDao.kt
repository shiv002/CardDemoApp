package com.dance.carddemoapp.interfaces

import androidx.room.*
import com.dance.carddemoapp.models.UserModel
/**
 * @author:Shivani Bajpai
 * @description: Dao class for User Model
 */
@Dao
interface UserDao {
    @Insert
    fun addData(mydatalist: UserModel?)

    @Query("select * from userList")
    fun getMyData(): List<UserModel?>?

    @Delete
    fun delete(mydatalist: UserModel?)

    @Update
    fun update(mydatalist: UserModel?)


}