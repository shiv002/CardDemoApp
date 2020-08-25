package com.dance.carddemoapp.models

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author:Shivani Bajpai
 * @description: Room Entity class for User Model
 */
@Entity(tableName = "userList")
@Parcelize
data class UserModel(

    @PrimaryKey(autoGenerate = true)
    var Id:Int,

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    @Expose
    var gender: String,

    @Embedded
    @SerializedName("name")
    @Expose
    var name: NameModel,

    @Embedded
    @Expose
    var location: LocationModel,

    @ColumnInfo(name = "email")
    @SerializedName("email")
    @Expose
    var email: String,

    @ColumnInfo(name = "username")
    @SerializedName("username")
    @Expose
    var username: String,

    @ColumnInfo(name = "password")
    @SerializedName("password")
    @Expose
    var password: String,

    @ColumnInfo(name = "salt")
    @SerializedName("salt")
    @Expose
    var salt: String,

    @ColumnInfo(name = "md5")
    @SerializedName("md5")
    @Expose
    var md5: String,

    @ColumnInfo(name = "sha1")
    @SerializedName("sha1")
    @Expose
    var sha1: String,

    @ColumnInfo(name = "sha256")
    @SerializedName("sha256")
    @Expose
    var sha256: String,

    @ColumnInfo(name = "registered")
    @SerializedName("registered")
    @Expose
    var registered: String,

    @ColumnInfo(name = "dob")
    @SerializedName("dob")
    @Expose
    var dob: String,

    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    @Expose
    var phone: String,

    @ColumnInfo(name = "cell")
    @SerializedName("cell")
    @Expose
    var cell: String,

    @ColumnInfo(name = "SSN")
    @SerializedName("SSN")
    @Expose
    var sSN: String,

    @ColumnInfo(name = "picture")
    @SerializedName("picture")
    @Expose
    var picture: String
) : Parcelable