package com.dance.carddemoapp.models

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
/**
 * @author:Shivani Bajpai
 * @description: Room Entity class for Name Model
 */
@Entity
@Parcelize
data class NameModel(
    @PrimaryKey(autoGenerate = true)
    var nameId:Int ,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    var title: String,

    @ColumnInfo(name = "first")
    @SerializedName("first")
    @Expose
    var firstString: String,

    @ColumnInfo(name = "last")
    @SerializedName("last")
    @Expose
    var last: String
) : Parcelable