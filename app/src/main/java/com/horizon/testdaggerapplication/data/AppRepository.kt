package com.horizon.testdaggerapplication.data

import androidx.lifecycle.LiveData
import com.horizon.testdaggerapplication.data.model.Restaurant
import javax.inject.Inject

class AppRepository @Inject constructor(private val localDataSource: RestaurantDao) {

    fun getRestaurants(): LiveData<List<Restaurant>> {
        return localDataSource.getAllRestaurants()
    }

    suspend fun saveRestaurants(restaurants: List<Restaurant>) {
        return localDataSource.insertAll(restaurants)
    }
}