package com.dance.carddemoapp.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dance.carddemoapp.interfaces.LocationDao
import com.dance.carddemoapp.interfaces.NameDao
import com.dance.carddemoapp.interfaces.UserDao
import com.dance.carddemoapp.models.LocationModel
import com.dance.carddemoapp.models.NameModel
import com.dance.carddemoapp.models.UserModel

/**
 * @author:Main Room Database abstract class for all three UserModel
 */
@Database(entities = [UserModel::class, NameModel::class, LocationModel::class], version = 1)
abstract class MyDataBase : RoomDatabase() {
    abstract fun myDao(): UserDao
    abstract fun nameDao(): NameDao
    abstract fun locDao(): LocationDao

    companion object {
        private var INSTANCE: MyDataBase? = null

        fun getMyDataBase(context: Context): MyDataBase? {
            if (INSTANCE == null) {
                synchronized(MyDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MyDataBase::class.java,
                        "myDB"
                    ).build()
                }
            }
            return INSTANCE
        }


    }
}


