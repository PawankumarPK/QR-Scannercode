package com.example.qr_scanner.fragments


import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
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

            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer,ScannerFragment()).commit()
                    return null
                }
            }.execute()

                //fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer,ScannerFragment()).commit()
                //  openDrawerActivity()

        }



    }
    private fun openDrawerActivity() {
        Handler().postDelayed({
            fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer,ScannerFragment()).commit()
        }, 200)
    }




}
