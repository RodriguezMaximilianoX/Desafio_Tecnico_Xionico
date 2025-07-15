package com.rmxdev.ventapp.domain.repository

import com.rmxdev.ventapp.domain.entities.Sale
import kotlinx.coroutines.flow.Flow

interface SaleRepository {
    suspend fun insertSale(sale: Sale)
    suspend fun insertSales(sales: List<Sale>)
    fun getSalesByClient(clientId: Int): Flow<List<Sale>>
    suspend fun deleteSale(saleId: Int)
}