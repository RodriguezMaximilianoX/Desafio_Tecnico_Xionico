package com.rmxdev.ventapp.presenter.register

sealed class RegisterState {
    data object Idle: RegisterState()
    data object Loading: RegisterState()
    data object Success: RegisterState()
    data class Error(val message: String): RegisterState()
}