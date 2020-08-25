package com.dance.carddemoapp.interfaces

import com.dance.carddemoapp.models.MainCardModel
import retrofit2.http.GET

/**
 * @author:Shivani Bajpai
 * @description: interface which manages api results can vary by changing result size
 */
interface ProfileApi {
    @GET("/api/0.4/?results=20")
    suspend fun getProfiles(): MainCardModel


}