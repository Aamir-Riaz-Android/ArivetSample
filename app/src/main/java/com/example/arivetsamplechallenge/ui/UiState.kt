package com.example.arivetsamplechallenge.ui

sealed class UiState

object LoadingState:UiState()
object ContentState:UiState()
object ErrorState:UiState()