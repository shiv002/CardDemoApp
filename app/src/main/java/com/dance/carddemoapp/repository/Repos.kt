package com.dance.carddemoapp.repository

import com.dance.carddemoapp.interfaces.ProfileApi

/**
 * @author:Shivani Bajpai
 * @description: Repository class who act intermediate between viewModelFactory and viewModel
 */
class Repos(private val apiService: ProfileApi) {
    suspend fun mGetProfiles() = apiService.getProfiles()
}