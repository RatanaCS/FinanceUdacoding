package com.sumuzu.financeudacoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.model.getDataProduk.DataItemProduk
import kotlinx.android.synthetic.main.list_item_produk.view.*

class DataProdukAdapter(val data :List<DataItemProduk>? , val itemClick : OnClickListener):RecyclerView.Adapter<DataProdukAdapter.ViewHolder>() {
    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {

        val nama = view.tvNamaProduk
        val kategori = view.tvKategoriProduk
        val hBeli = view.tvHargaBeli
        val hJual = view.tvHargaJual
        val stok = view.tvStok
        val satuan = view.tvSatuan

//        val delete = view.ivDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_produk, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : DataItemProduk? =data?.get(position)

        holder.nama.text = item?.nama
//        holder.kategori.text = item?.id_kategori
        holder.kategori.text = item?.kategori
        holder.hBeli.text = item?.harga_beli
        holder.hJual.text = item?.harga_jual
        holder.stok.text = item?.stok
        holder.satuan.text = item?.satuan

        holder.view.setOnClickListener {
            itemClick.detail(item)
        }

//        holder.delete.setOnClickListener {
//            itemClick.delete(item)
//        }



    }

    override fun getItemCount(): Int = data?.size ?:0

    interface OnClickListener{
        fun detail(item: DataItemProduk?)
        fun delete(item: DataItemProduk?)
    }
}