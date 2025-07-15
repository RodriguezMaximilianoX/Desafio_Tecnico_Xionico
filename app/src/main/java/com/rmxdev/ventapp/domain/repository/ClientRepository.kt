package com.rmxdev.ventapp.domain.repository

import com.rmxdev.ventapp.domain.entities.Client
import kotlinx.coroutines.flow.Flow

interface ClientRepository {
    fun getAllClients(): Flow<List<Client>>
    fun searchClients(query: String): Flow<List<Client>>
    suspend fun insertClient(client: Client)
    suspend fun insertClients(clients: List<Client>)
    suspend fun markClientAsSold(clientId: Int)
}