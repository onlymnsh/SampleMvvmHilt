package com.horizon.testdaggerapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RestaurantApplication : Application() {

    override fun onCreate() {
        super.onCreate()
//        val appComponent = DaggerI.create()

    }


}