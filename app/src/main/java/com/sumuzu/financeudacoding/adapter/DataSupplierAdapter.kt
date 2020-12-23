package com.sumuzu.financeudacoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.model.getDataSupplier.DataItemSupplier
import kotlinx.android.synthetic.main.list_item_supplier.view.*

class DataSupplierAdapter(val data :List<DataItemSupplier>?, val itemClick : OnClickListener):RecyclerView.Adapter<DataSupplierAdapter.ViewHolder>() {
    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {

        val nama = view.tvNamaSupplier
        val noTlp = view.tvNoTlp
        val alamat = view.tvAlamat


//        val delete = view.ivDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_supplier, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : DataItemSupplier? =data?.get(position)

        holder.nama.text = item?.nama
        holder.noTlp.text = item?.no_tlp
        holder.alamat.text = item?.alamat


        holder.view.setOnClickListener {
            itemClick.detail(item)
        }

//        holder.delete.setOnClickListener {
//            itemClick.delete(item)
//        }



    }

    override fun getItemCount(): Int = data?.size ?:0

    interface OnClickListener{
        fun detail(item: DataItemSupplier?)
        fun delete(item: DataItemSupplier?)
    }
}