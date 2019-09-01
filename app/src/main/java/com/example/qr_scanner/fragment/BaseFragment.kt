package com.example.qr_scanner.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qr_scanner.activity.BaseActivity

open class BaseFragment : Fragment() {
    
    lateinit var baseActivity : BaseActivity
    
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        
        baseActivity = activity as BaseActivity
    }
    
    fun toast(value : Any) {
        Toast.makeText(baseActivity, value.toString(), Toast.LENGTH_SHORT).show()
    }
}