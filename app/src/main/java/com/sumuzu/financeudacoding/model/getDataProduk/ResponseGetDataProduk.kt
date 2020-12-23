package com.sumuzu.financeudacoding.model.getDataProduk

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetDataProduk(
    @field:SerializedName("data")
    val data: List<DataItemProduk>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean? = null
):Parcelable
