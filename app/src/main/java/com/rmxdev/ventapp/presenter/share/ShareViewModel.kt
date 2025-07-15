package com.rmxdev.ventapp.presenter.share

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rmxdev.ventapp.core.shareJsonFile
import com.rmxdev.ventapp.domain.repository.ClientRepository
import com.rmxdev.ventapp.domain.repository.InvoiceRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ShareViewModel(
    private val clientRepository: ClientRepository,
    private val invoiceRepository: InvoiceRepository,
    private val context: Application
) : ViewModel() {
    fun exportClients() {
        viewModelScope.launch {
            val clients = clientRepository.getAllClients().first()
            val json = Gson().toJson(clients)
            val file = context.shareJsonFile("clientes.json", json, "Listado de Clientes")
        }
    }

    fun exportInvoices() {
        viewModelScope.launch {
            val invoices = invoiceRepository.getAllInvoices().first()
            val json = Gson().toJson(invoices)
            val file = context.shareJsonFile("reporte_ventas.json", json, "Reporte de Ventas")
        }
    }
}