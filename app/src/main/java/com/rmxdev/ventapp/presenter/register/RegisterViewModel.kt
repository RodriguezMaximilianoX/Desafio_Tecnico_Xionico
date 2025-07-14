package com.rmxdev.ventapp.presenter.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.ventapp.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val useCase: RegisterUseCase
): ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun register(email: String, name: String, password: String, confirmPassword: String) {
        _registerState.value = RegisterState.Loading
        viewModelScope.launch {
            val result = useCase(email, name, password, confirmPassword)
            _registerState.value = result.fold(
                onSuccess = { RegisterState.Success },
                onFailure = { RegisterState.Error(it.message ?: "Error al crear el usuario") }
            )
        }
    }
}