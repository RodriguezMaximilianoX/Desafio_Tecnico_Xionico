package com.rmxdev.ventapp.presenter.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.ventapp.domain.repository.ClientRepository
import com.rmxdev.ventapp.domain.repository.InvoiceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MapsViewModel(
    private val invoiceRepository: InvoiceRepository,
    private val clientRepository: ClientRepository
) : ViewModel() {

    val markers: StateFlow<List<SaleMarker>> = flow {
        val clients = clientRepository.getAllClients().first().filter {
            it.hasSale
        }
        val invoices = invoiceRepository.getAllInvoices().first()

        val merged = clients.mapNotNull { client ->
            val invoice = invoices.find { it.clientId == client.id }
            invoice?.let {
                SaleMarker(
                    latitude = client.latitude,
                    longitude = client.longitude,
                    clientName = client.name,
                    sellerName = it.sellerName,
                    amount = it.totalAmount,
                    quantity = it.totalProducts
                )
            }
        }
        emit(merged)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}

