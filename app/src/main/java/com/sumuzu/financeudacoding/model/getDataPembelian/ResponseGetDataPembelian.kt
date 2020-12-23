package com.sumuzu.financeudacoding.model.getDataPembelian

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetDataPembelian(
    @field:SerializedName("data")
    val data: List<DataItemPembelian>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean? = null
):Parcelable
