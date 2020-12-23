package com.sumuzu.financeudacoding.model.getDataSupplier

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetDataSupplier(
    @field:SerializedName("data")
    val data: List<DataItemSupplier>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean? = null
):Parcelable
