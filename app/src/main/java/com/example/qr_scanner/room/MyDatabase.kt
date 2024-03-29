package com.example.qr_scanner.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StockItems :: class],version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao() : MyDao
}