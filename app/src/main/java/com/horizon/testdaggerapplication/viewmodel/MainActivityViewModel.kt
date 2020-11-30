package com.horizon.testdaggerapplication.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.horizon.testdaggerapplication.data.AppRepository
import com.horizon.testdaggerapplication.data.model.Restaurant
import com.horizon.testdaggerapplication.data.model.RestaurantResult
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(private val repository: AppRepository) :
    ViewModel() {

    val restaurant: LiveData<List<Restaurant>> = repository.getRestaurants()

    fun parseJsonString(jsonFileString: String) {
        val restaurantType = object : TypeToken<RestaurantResult>() {}.type
        val restaurantResult: RestaurantResult = Gson().fromJson(jsonFileString, restaurantType)
        viewModelScope.launch {
            if (restaurant.value.isNullOrEmpty()) {
                repository.saveRestaurants(restaurantResult.restaurants)
            }
        }
    }


}