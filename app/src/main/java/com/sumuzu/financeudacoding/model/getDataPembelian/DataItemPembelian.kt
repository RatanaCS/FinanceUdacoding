package com.sumuzu.financeudacoding.model.getDataPembelian

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItemPembelian(

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("nominal")
    val nominal: Int? = null,

    @field:SerializedName("tgl_transaksi")
    val tgl_transaksi: String? = null,

    @field:SerializedName("id")
    val id: String? = null

):Parcelable

