package com.rmxdev.ventapp.presenter.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.ventapp.domain.entities.Invoice
import com.rmxdev.ventapp.domain.repository.InvoiceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class SalesReportViewModel(
    private val invoiceRepository: InvoiceRepository
): ViewModel() {
    val invoices: StateFlow<List<Invoice>> = invoiceRepository
        .getAllInvoices()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}