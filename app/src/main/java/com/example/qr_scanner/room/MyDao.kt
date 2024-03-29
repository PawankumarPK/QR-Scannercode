package com.example.qr_scanner.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MyDao {

    @Insert
    fun insertItems(stockItems : StockItems)

    @Delete
    fun deleteItems(stockItems: StockItems)

    @get: Query("select * from stockTable")
    val stockList : List<StockItems>

}