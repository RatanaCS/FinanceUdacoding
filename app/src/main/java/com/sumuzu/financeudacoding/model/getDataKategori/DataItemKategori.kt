package com.sumuzu.financeudacoding.model.getDataKategori

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItemKategori(

    @field:SerializedName("kategori")
    val kategori: String? = null,

    @field:SerializedName("id")
    val id: String? = null

):Parcelable

