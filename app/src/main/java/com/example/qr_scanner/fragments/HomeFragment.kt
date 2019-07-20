package com.example.qr_scanner.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qr_scanner.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAddCardview.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, ScannerFragment()).commit()
        }//check mode
    }
}
