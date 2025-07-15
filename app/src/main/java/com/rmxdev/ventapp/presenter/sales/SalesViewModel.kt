package com.rmxdev.ventapp.presenter.sales

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.ventapp.domain.entities.Article
import com.rmxdev.ventapp.domain.entities.Client
import com.rmxdev.ventapp.domain.entities.Invoice
import com.rmxdev.ventapp.domain.entities.Sale
import com.rmxdev.ventapp.domain.repository.ArticleRepository
import com.rmxdev.ventapp.domain.repository.ClientRepository
import com.rmxdev.ventapp.domain.repository.InvoiceRepository
import com.rmxdev.ventapp.domain.repository.SaleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SalesViewModel(
    private val saleRepository: SaleRepository,
    private val clientRepository: ClientRepository,
    private val invoiceRepository: InvoiceRepository,
    private val articleRepository: ArticleRepository
): ViewModel() {
    private val _saleItems = MutableStateFlow<List<Sale>>(emptyList())
    val saleItems: StateFlow<List<Sale>> = _saleItems

    private val _totalAmount = MutableStateFlow(0.0)
    val totalAmount: StateFlow<Double> = _totalAmount

    private val _totalProducts = MutableStateFlow(0)
    val totalProducts: StateFlow<Int> = _totalProducts

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _allArticles = articleRepository
        .getAllArticles()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val filteredArticles: StateFlow<List<Article>> = _searchQuery
        .combine(_allArticles) { query, articles ->
            if (query.isBlank()) {
                articles
            } else {
                articles.filter { it.name.contains(query, ignoreCase = true) }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addSaleItem(clientId: Int, productName: String, quantity: Int, price: Double) {
        val newItem = Sale(
            clientId = clientId,
            productName = productName,
            quantity = quantity,
            unitPrice = price
        )

        _saleItems.value += newItem
        recalculateTotals()
    }

    fun removeSaleItem(index: Int) {
        _saleItems.value = _saleItems.value.toMutableList().apply { removeAt(index) }
        recalculateTotals()
    }

    private fun recalculateTotals() {
        _totalAmount.value = _saleItems.value.sumOf { it.quantity * it.unitPrice }
        _totalProducts.value = _saleItems.value.sumOf { it.quantity }
    }

    fun confirmSale(client: Client, sellerName: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            // Guardar ventas
            saleRepository.insertSales(_saleItems.value)

            // Crear factura
            val invoice = Invoice(
                clientId = client.id,
                clientName = client.name,
                sellerName = sellerName,
                totalAmount = _totalAmount.value,
                totalProducts = _totalProducts.value
            )
            invoiceRepository.insertInvoice(invoice)

            // Marcar cliente como vendido
            clientRepository.markClientAsSold(client.id)

            onComplete()
        }
    }

    fun clearSale() {
        _saleItems.value = emptyList()
        _totalAmount.value = 0.0
        _totalProducts.value = 0
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}