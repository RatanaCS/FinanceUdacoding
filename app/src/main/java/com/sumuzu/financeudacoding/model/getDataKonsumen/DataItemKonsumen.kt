package com.sumuzu.financeudacoding.model.getDataKonsumen

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItemKonsumen(

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("no_tlp")
    val no_tlp: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null,

    @field:SerializedName("id")
    val id: String? = null

):Parcelable

