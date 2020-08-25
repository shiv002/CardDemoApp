package com.dance.carddemoapp.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
/**
 * @author:Shivani Bajpai
 * @description: Model class who takes result arraylist from api
 */
@Parcelize
data class MainCardModel(@SerializedName("results")
                         @Expose
                         var result:ArrayList<ResultModel> ):Parcelable