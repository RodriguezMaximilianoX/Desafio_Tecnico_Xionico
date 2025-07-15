package com.rmxdev.ventapp.presenter.share

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rmxdev.ventapp.core.exportJsonToFile
import com.rmxdev.ventapp.domain.repository.ClientRepository
import com.rmxdev.ventapp.domain.repository.InvoiceRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File

class ShareViewModel(
    private val clientRepository: ClientRepository,
    private val invoiceRepository: InvoiceRepository,
    private val context: Application
): ViewModel() {
    fun exportClients(onExported: (File) -> Unit) {
        viewModelScope.launch {
            val clients = clientRepository.getAllClients().first()
            val json = Gson().toJson(clients)
            val file = context.exportJsonToFile("clientes.json", json)
            onExported(file)
        }
    }

    fun exportInvoices(onExported: (File) -> Unit) {
        viewModelScope.launch {
            val invoices = invoiceRepository.getAllInvoices().first()
            val json = Gson().toJson(invoices)
            val file = context.exportJsonToFile("ventas.json", json)
            onExported(file)
        }
    }
}