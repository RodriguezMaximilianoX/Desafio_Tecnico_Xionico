package com.rmxdev.ventapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rmxdev.ventapp.domain.entities.Sale
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSale(sale: Sale)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSales(sales: List<Sale>)

    @Query("SELECT * FROM sales WHERE clientId = :clientId")
    fun getSalesByClient(clientId: Int): Flow<List<Sale>>

    @Query("DELETE FROM sales WHERE id = :saleId")
    suspend fun deleteSale(saleId: Int)
}