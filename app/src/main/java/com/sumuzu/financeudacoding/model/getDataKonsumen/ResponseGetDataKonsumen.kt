package com.sumuzu.financeudacoding.model.getDataKonsumen

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetDataKonsumen(
    @field:SerializedName("data")
    val data: List<DataItemKonsumen>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean? = null
):Parcelable
