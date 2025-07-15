package com.rmxdev.ventapp.domain.repository

import com.rmxdev.ventapp.domain.entities.Invoice
import kotlinx.coroutines.flow.Flow

interface InvoiceRepository {
    suspend fun insertInvoice(invoice: Invoice)
    fun getAllInvoices(): Flow<List<Invoice>>
    fun getInvoicesByClient(clientId: Int): Flow<List<Invoice>>
}