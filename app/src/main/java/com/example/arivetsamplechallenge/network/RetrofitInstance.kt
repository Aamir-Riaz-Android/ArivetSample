package com.example.arivetsamplechallenge.network

import com.example.arivetsamplechallenge.BuildConfig
import com.example.arivetsamplechallenge.utils.Constansts.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {
        private val logging by lazy {

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            } else {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            }
        }
        private val httpClient by lazy {

            OkHttpClient.Builder().addInterceptor(logging)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)

        }
        private val retrofit by lazy {


            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient?.build())
                .build()
        }
        val api by lazy {
            retrofit.create(UserDetailsApi::class.java)
        }
    }
}