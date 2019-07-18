package com.example.qr_scanner.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qr_scanner.R
import com.example.qr_scanner.fragments.HomeFragment


class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        loadFragment()
    }

    private fun loadFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mFrameContainer, HomeFragment())
            .addToBackStack(null).commit()
    }
}

