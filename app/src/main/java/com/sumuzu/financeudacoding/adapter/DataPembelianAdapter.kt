package com.sumuzu.financeudacoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.model.getDataPembelian.DataItemPembelian
import kotlinx.android.synthetic.main.list_item_pembelian.view.*

class DataPembelianAdapter(val data :List<DataItemPembelian>?, val itemClick : OnClickListener):RecyclerView.Adapter<DataPembelianAdapter.ViewHolder>() {
    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {

        val id = view.tvIdPembelian
        val nama = view.tvNamaSupplier
        val nominal = view.tvNominal
        val tgl_transaksi = view.tvTglTransaksi

//        val delete = view.ivDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_pembelian, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : DataItemPembelian? =data?.get(position)

        holder.id.text = item?.id
        holder.nama.text = item?.nama
        holder.nominal.text = item?.nominal.toString()
        holder.tgl_transaksi.text = item?.tgl_transaksi

        holder.view.setOnClickListener {
            itemClick.detail(item)
        }

//        holder.delete.setOnClickListener {
//            itemClick.delete(item)
//        }



    }

    override fun getItemCount(): Int = data?.size ?:0

    interface OnClickListener{
        fun detail(item: DataItemPembelian?)
        fun delete(item: DataItemPembelian?)
    }
}