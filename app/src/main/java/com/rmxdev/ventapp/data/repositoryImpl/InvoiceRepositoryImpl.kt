package com.rmxdev.ventapp.data.repositoryImpl

import com.rmxdev.ventapp.data.dao.InvoiceDao
import com.rmxdev.ventapp.domain.entities.Invoice
import com.rmxdev.ventapp.domain.repository.InvoiceRepository
import kotlinx.coroutines.flow.Flow

class InvoiceRepositoryImpl(
    private val invoiceDao: InvoiceDao
): InvoiceRepository {
    override suspend fun insertInvoice(invoice: Invoice) {
        invoiceDao.insertInvoice(invoice)
    }

    override fun getAllInvoices(): Flow<List<Invoice>> {
        return invoiceDao.getAllInvoices()
    }

    override fun getInvoicesByClient(clientId: Int): Flow<List<Invoice>> {
        return invoiceDao.getInvoicesByClient(clientId)
    }
}