package com.example.qr_scanner.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.qr_scanner.R
import com.example.qr_scanner.fragment.HomeFragment
import com.example.qr_scanner.room.MyDatabase


class BaseActivity : AppCompatActivity() {

    companion object {
        var INSTANCE: MyDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        loadFragment()
        INSTANCE = getAppDatabase(applicationContext)
    }

    private fun getAppDatabase(context: Context): MyDatabase {
        if (INSTANCE == null) {
            synchronized(MyDatabase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "StockDB")
                    .allowMainThreadQueries()
                    .build()
            }
        }

        return INSTANCE!!
    }

    private fun loadFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mFrameContainer, HomeFragment())
            .addToBackStack(null).commit()

    }
}

