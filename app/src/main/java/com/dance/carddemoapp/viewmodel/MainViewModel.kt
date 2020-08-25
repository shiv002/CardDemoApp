package com.dance.carddemoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dance.carddemoapp.repository.Repos
import com.dance.carddemoapp.utils.Resource
import kotlinx.coroutines.Dispatchers

/**
 * @author:Shivani Bajpai
 * @description: ViewModel + LiveData to connect view to backend or model (abstraction)
 */
class MainViewModel(private val mainRepositry: Repos) : ViewModel() {
    fun getProfileList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepositry.mGetProfiles()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}