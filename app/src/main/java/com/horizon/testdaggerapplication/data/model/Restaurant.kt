package com.horizon.testdaggerapplication.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurant")
data class Restaurant(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") var status: String,
    @Embedded @SerializedName("sortingValues") var sortingValues: SortingValues,
) {

    data class SortingValues(
        @SerializedName("bestMatch") var bestMatch: Float,
        @SerializedName("newest") var newest: Float,
        @SerializedName("ratingAverage") var ratingAverage: Float,
    )
}