package com.rmxdev.ventapp.presenter.register

import com.rmxdev.ventapp.domain.entities.User

sealed class RegisterState {
    data object Idle: RegisterState()
    data object Loading: RegisterState()
    data class Success(val user: User): RegisterState()
    data class Error(val message: String): RegisterState()
}