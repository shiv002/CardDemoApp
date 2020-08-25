package com.dance.carddemoapp.utils

import com.dance.carddemoapp.interfaces.ProfileApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author:Shivani Bajpai
 * @description: Singleton Retrofitbuilder class
 */
object Retrofitbuilder {
    private const val BASE_URL = "https://randomuser.me/"

    private fun httpInterceptor(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpInterceptor().build())
            .build()
    }

    val apiService: ProfileApi = getRetrofit().create(ProfileApi::class.java)


}