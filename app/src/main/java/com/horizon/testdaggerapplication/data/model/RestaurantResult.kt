package com.horizon.testdaggerapplication.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantResult(
    @SerializedName("restaurants") val restaurants: List<Restaurant>
) {

}