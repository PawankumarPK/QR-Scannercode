package com.example.qr_scanner.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qr_scanner.R
import kotlinx.android.synthetic.main.view_holder.view.*







class ScannerAdapter(private var dataList: ArrayList<String> ) : RecyclerView.Adapter<ScannerAdapter.ViewHolder>() {


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
        @SuppressLint("SetTextI18n")
        fun bindItems(pos : Int) {
            val scanData = dataList[pos]
            itemView.mQRText.text = scanData
            itemView.mCount.text = Integer.toString(pos+1)
            itemView.mDelete.setOnClickListener {
                    removeAt(pos)
            }
        }

        private fun removeAt(position: Int){
            dataList.removeAt(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()
            notifyItemRangeChanged(position, dataList.size)

            // dataList.removeAt(position)
        }
    }

}