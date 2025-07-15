package com.rmxdev.ventapp.data.repositoryImpl

import com.rmxdev.ventapp.data.dao.ClientDao
import com.rmxdev.ventapp.domain.entities.Client
import com.rmxdev.ventapp.domain.repository.ClientRepository
import kotlinx.coroutines.flow.Flow

class ClientRepositoryImpl(
    private val clientDao: ClientDao
): ClientRepository {
    override fun getAllClients(): Flow<List<Client>> {
        return clientDao.getAllClients()
    }

    override fun searchClients(query: String): Flow<List<Client>> {
        return clientDao.searchClients(query)
    }

    override suspend fun insertClient(client: Client) {
        clientDao.insert(client)
    }

    override suspend fun insertClients(clients: List<Client>) {
        clientDao.insertAll(clients)
    }

    override suspend fun markClientAsSold(clientId: Int) {
        clientDao.markClientAsSold(clientId)
    }

}