package com.horizon.testdaggerapplication.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.horizon.testdaggerapplication.data.AppDatabase
import com.horizon.testdaggerapplication.data.AppRepository
import com.horizon.testdaggerapplication.data.RestaurantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MainModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideRestaurantDao(db: AppDatabase) = db.restaurantDao()

    @Singleton
    @Provides
    fun provideRepository(localDataSource: RestaurantDao) = AppRepository(localDataSource)

}