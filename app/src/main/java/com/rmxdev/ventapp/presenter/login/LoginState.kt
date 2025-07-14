package com.rmxdev.ventapp.presenter.login

import com.rmxdev.ventapp.domain.entities.User

sealed class LoginState {
    data object Idle: LoginState()
    data object Loading: LoginState()
    data class Success(val user: User): LoginState()
    data class Error(val message: String): LoginState()
}