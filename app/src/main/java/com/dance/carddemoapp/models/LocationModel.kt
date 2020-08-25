package com.dance.carddemoapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author:Shivani Bajpai
 * @description: Room Entity class for locationModel
 */
@Entity
@Parcelize
data class LocationModel(

    @PrimaryKey(autoGenerate = true)
    var locId: Int,

    @ColumnInfo(name = "street")
    @SerializedName("street")
    @Expose
    var street: String,

    @ColumnInfo(name = "city")
    @SerializedName("city")
    @Expose
    var city: String,

    @ColumnInfo(name = "state")
    @SerializedName("state")
    @Expose
    var state: String,

    @ColumnInfo(name = "zip")
    @SerializedName("zip")
    @Expose
    var zip: String

) : Parcelable
