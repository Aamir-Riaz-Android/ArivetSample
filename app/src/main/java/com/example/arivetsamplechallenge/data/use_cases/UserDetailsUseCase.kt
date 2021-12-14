package com.example.arivetsamplechallenge.data.use_cases

import com.example.arivetsamplechallenge.data.remote.UserDetailsRepository
import javax.inject.Inject

class UserDetailsUseCase@Inject constructor(private val repository: UserDetailsRepository) {
   // suspend operator fun invoke() = repository.getList()
}