package com.example.arivetsamplechallenge.network

import com.example.arivetsamplechallenge.api_response.UserDetails
import retrofit2.Response
import retrofit2.http.GET

interface UserDetailsApi {
    @GET("?results=200")
    suspend fun getUserDetails(): Response<UserDetails>
}