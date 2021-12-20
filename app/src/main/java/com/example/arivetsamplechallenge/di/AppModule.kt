package com.example.arivetsamplechallenge.di

import android.content.Context
import com.example.arivetsamplechallenge.data.local.AppDatabase
import com.example.arivetsamplechallenge.data.local.dao.UserDao
import com.example.arivetsamplechallenge.network.RetrofitInstance
import com.example.arivetsamplechallenge.network.UserDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRoomDbUserDao( @ApplicationContext context:Context): UserDao
    = AppDatabase.getDatabase(context).userDetailsDao()


    @Provides
    fun provideUserDetailsApi(): UserDetailsApi
            = RetrofitInstance.api
}