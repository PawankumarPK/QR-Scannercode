package com.example.qr_scanner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qr_scanner.R
import kotlinx.android.synthetic.main.view_holder.view.*

class ScannerAdapter : RecyclerView.Adapter<ScannerAdapter.ViewHolder>() {

    private var dataList: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent, false))
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(pos : Int) {
            val scanData = dataList[pos]
            itemView.mQRText.text = scanData
        }
    }

}