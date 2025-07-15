package com.rmxdev.ventapp.presenter.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.ventapp.core.FakeArticleData
import com.rmxdev.ventapp.core.FakeClientData
import com.rmxdev.ventapp.data.dao.ArticleDao
import com.rmxdev.ventapp.data.dao.ClientDao
import com.rmxdev.ventapp.domain.entities.Client
import com.rmxdev.ventapp.domain.repository.ClientRepository
import com.rmxdev.ventapp.domain.usecase.GetClientsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class ClientViewModel(
    private val getClientsUseCase: GetClientsUseCase,
    private val clientRepository: ClientRepository
): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _clients = MutableStateFlow<List<Client>>(emptyList())
    val clients: StateFlow<List<Client>> = _clients

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .flatMapLatest { query ->
                    if (query.isBlank()) {
                        getClientsUseCase()
                    } else {
                        clientRepository.searchClients(query)
                    }
                }
                .collect { list ->
                    _clients.value = list
                }
        }
    }

    fun onSearchChanged(query: String){
        _searchQuery.value = query
    }

}