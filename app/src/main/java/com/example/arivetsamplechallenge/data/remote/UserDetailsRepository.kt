package com.example.arivetsamplechallenge.data.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.arivetsamplechallenge.api_response.UsersInfo
import com.example.arivetsamplechallenge.data.local.dao.UserDao
import com.example.arivetsamplechallenge.network.UserDetailsApi
import com.example.arivetsamplechallenge.ui.ContentState
import com.example.arivetsamplechallenge.ui.ErrorState
import com.example.arivetsamplechallenge.ui.LoadingState
import com.example.arivetsamplechallenge.ui.UiState
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class UserDetailsRepository @Inject constructor(
    private val userDetailsApi: UserDetailsApi, private val userDao: UserDao
) {
    private val _uiState by lazy {
        MutableLiveData<UiState>()
    }
    private val _userDetails by lazy {
        MutableLiveData<UsersInfo>()
    }

    fun getUiUpdates(): MutableLiveData<UiState> = _uiState
    fun getUserDetailsUpdates(): MutableLiveData<UsersInfo> = _userDetails

    suspend fun getSingleUserDetails(someProvideId: Long): MutableLiveData<UsersInfo> {
        _userDetails.postValue(userDao.getSingleUserDetail(someProvideId))
        return _userDetails
    }

    suspend fun checkIsDbEmpty() {
        coroutineScope {
            _uiState.postValue(LoadingState)
            val list = userDao.getUsersDataList(1)// better than count db function which will count 200 records
            if (list.isEmpty())
                getUserDetails() // call web service
            else _uiState.postValue(ContentState) // all good get data from database
        }
    }

    suspend fun getUserDetails() {
        coroutineScope {
            val response = userDetailsApi.getUserDetails()
            if (response.isSuccessful) {
                response.body()?.let {
                    userDao?.addUsersDetails(response?.body()?.results)
                    _uiState.postValue(ContentState)
                   // Log.e("hi", "success")
                }

            } else {
              //  Log.e("hi", "failure")
                _uiState.postValue(ErrorState)
            }
        }

    }

}