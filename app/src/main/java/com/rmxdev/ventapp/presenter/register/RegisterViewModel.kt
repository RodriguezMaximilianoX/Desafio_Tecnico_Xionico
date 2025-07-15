package com.rmxdev.ventapp.presenter.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.ventapp.core.FakeArticleData
import com.rmxdev.ventapp.core.FakeClientData
import com.rmxdev.ventapp.data.dao.ArticleDao
import com.rmxdev.ventapp.data.dao.ClientDao
import com.rmxdev.ventapp.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val useCase: RegisterUseCase,
    private val clientDao: ClientDao,
    private val articleDao: ArticleDao
): ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun register(email: String, name: String, password: String, confirmPassword: String) {
        _registerState.value = RegisterState.Loading
        viewModelScope.launch {
            val result = useCase(email, name, password, confirmPassword)
            _registerState.value = result.fold(
                onSuccess = { user -> RegisterState.Success(user) },
                onFailure = { RegisterState.Error(it.message ?: "Error al crear el usuario") }
            )
            preloadClientsIfEmpty()
            preloadArticlesIfEmpty()
        }
    }

    private suspend fun preloadClientsIfEmpty() {
        val count = clientDao.getClientsCount()
        if (count == 0) {
            val clients = FakeClientData.generateClients()
            clientDao.insertAll(clients)
        }
    }

    private suspend fun preloadArticlesIfEmpty() {
        val count = articleDao.getArticlesCount()
        if (count == 0) {
            FakeArticleData.insertRandomArticles(articleDao)
        }
    }
}