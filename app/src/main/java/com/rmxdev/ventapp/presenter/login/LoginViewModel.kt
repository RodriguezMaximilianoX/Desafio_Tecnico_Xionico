package com.rmxdev.ventapp.presenter.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.ventapp.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val useCase: LoginUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading
        viewModelScope.launch {
            val result = useCase(email, password)
            _loginState.value = result.fold(
                onSuccess = {
                    LoginState.Success(it)
                },
                onFailure = {
                    LoginState.Error(it.message ?: "Usuario o contraseña incorrectos")
                }
            )
        }
    }
}