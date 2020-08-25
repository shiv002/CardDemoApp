package com.dance.carddemoapp.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
/**
 * @author:Shivani Bajpai
 * @description: Result model who contains usermodel
 */

@Parcelize
data class ResultModel(
    @SerializedName("user")
    @Expose
    var user: UserModel,
    @SerializedName("seed")
    @Expose
    var seed: String,
    @SerializedName("version")
    @Expose
    var version: String
) : Parcelable