package com.example.arivetsamplechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.arivetsamplechallenge.api_response.UsersInfo
import com.example.arivetsamplechallenge.data.remote.UserDetailsRepository
import com.example.arivetsamplechallenge.paging.UserDetailsPagingSource
import com.example.arivetsamplechallenge.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private  val userDetailsRepository: UserDetailsRepository, private val userDataSource: UserDetailsPagingSource):ViewModel() {

    val  _uiState: LiveData<UiState> = userDetailsRepository.getUiUpdates()
    val  _userDetail:LiveData<UsersInfo> = userDetailsRepository.getUserDetailsUpdates()

    fun getDataFromRemoteSource(){
        viewModelScope.launch {
            userDetailsRepository.checkIsDbEmpty()
        }
    }
    fun getSingleUserDetail(idValue:Long){
        viewModelScope.launch {
            userDetailsRepository.getSingleUserDetails(idValue)
        }
    }

    fun geUserstListOfData(): Flow<PagingData<UsersInfo>> {
        return Pager(PagingConfig(30)) { userDataSource }
            .flow
            .cachedIn(viewModelScope)
    }


}