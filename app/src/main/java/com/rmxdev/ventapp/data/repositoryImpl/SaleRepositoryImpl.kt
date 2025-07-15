package com.rmxdev.ventapp.data.repositoryImpl

import com.rmxdev.ventapp.data.dao.SaleDao
import com.rmxdev.ventapp.domain.entities.Sale
import com.rmxdev.ventapp.domain.repository.SaleRepository
import kotlinx.coroutines.flow.Flow

class SaleRepositoryImpl(
    private val saleDao: SaleDao,
): SaleRepository {
    override suspend fun insertSale(sale: Sale) {
        saleDao.insertSale(sale)
    }

    override suspend fun insertSales(sales: List<Sale>) {
        saleDao.insertSales(sales)
    }

    override fun getSalesByClient(clientId: Int): Flow<List<Sale>> {
        return saleDao.getSalesByClient(clientId)
    }

    override suspend fun deleteSale(saleId: Int) {
        saleDao.deleteSale(saleId)
    }
}