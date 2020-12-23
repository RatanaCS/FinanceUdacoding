package com.sumuzu.financeudacoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.model.getDataKonsumen.DataItemKonsumen
import kotlinx.android.synthetic.main.list_item_konsumen.view.*

class DataKonsumenAdapter(val data :List<DataItemKonsumen>?, val itemClick : OnClickListener):RecyclerView.Adapter<DataKonsumenAdapter.ViewHolder>() {
    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {

        val nama = view.tvNamaKonsumen
        val noTlp = view.tvNoTlp
        val alamat = view.tvAlamat


//        val delete = view.ivDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_konsumen, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : DataItemKonsumen? =data?.get(position)

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
        fun detail(item: DataItemKonsumen?)
        fun delete(item: DataItemKonsumen?)
    }
}