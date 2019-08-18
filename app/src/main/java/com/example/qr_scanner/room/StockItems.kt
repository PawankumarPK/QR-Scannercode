package com.example.qr_scanner.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stockTable")
class StockItems {

    @PrimaryKey
    var id : Int? = null

    @ColumnInfo
    var itemName : String? = null

    @ColumnInfo
    var itemQuantity : Int? = null

}