package com.dance.carddemoapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dance.carddemoapp.interfaces.ProfileApi
import com.dance.carddemoapp.repository.Repos
import com.dance.carddemoapp.viewmodel.MainViewModel

/**
 * @author:Shivani Bajpai
 * @description: This class provide retfrofitbuilder to viewmodel in view . Its loose coupling process
 */
class ViewModelFactory(private val apiService: ProfileApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(Repos(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}