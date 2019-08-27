package com.example.qr_scanner.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.AsyncTask
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.room.Room
import com.example.qr_scanner.R
import com.example.qr_scanner.fragment.HomeFragment
import com.example.qr_scanner.room.MyDatabase
import com.google.android.gms.vision.CameraSource
import kotlinx.android.synthetic.main.fragment_addstocks.*
import java.io.IOException


class BaseActivity : AppCompatActivity() {
    
    val RequestCameraPermissionID = 1001
    private val displayRectangle = Rect()
    private var width = 0
    private lateinit var metrics : DisplayMetrics
    
    
    companion object {
        var INSTANCE: MyDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    
        INSTANCE = getAppDatabase(applicationContext)
        //addDisplayMatricsForDialog()
        loadFragment()
    }
    
    override fun onRequestPermissionsResult(requestCode : Int, permissions : Array<String>, grantResults : IntArray) {
        when (requestCode) {
            RequestCameraPermissionID -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(
                            this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        return
                    }
                }
            }
        }
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

