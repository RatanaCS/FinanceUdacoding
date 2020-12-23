package com.sumuzu.financeudacoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.model.getDataKategori.DataItemKategori
import kotlinx.android.synthetic.main.list_item_kategori.view.*

class DataKategoriAdapter(val data :List<DataItemKategori>?, val itemClick : OnClickListener):RecyclerView.Adapter<DataKategoriAdapter.ViewHolder>() {
    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {

        val kategori = view.tvNamaKategori
//        val delete = view.ivDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_kategori, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : DataItemKategori? =data?.get(position)

//        holder.kategori.text = item?.id_kategori
        holder.kategori.text = item?.kategori

        holder.view.setOnClickListener {
            itemClick.detail(item)
        }

//        holder.delete.setOnClickListener {
//            itemClick.delete(item)
//        }



    }

    override fun getItemCount(): Int = data?.size ?:0

    interface OnClickListener{
        fun detail(item: DataItemKategori?)
        fun delete(item: DataItemKategori?)
    }
}