package com.rmxdev.ventapp.domain.usecase

import com.rmxdev.ventapp.domain.entities.Client
import com.rmxdev.ventapp.domain.repository.ClientRepository
import kotlinx.coroutines.flow.Flow

class GetClientsUseCase(
    private val clientRepository: ClientRepository
) {
    operator fun invoke(): Flow<List<Client>>{
        return clientRepository.getAllClients()
    }
}