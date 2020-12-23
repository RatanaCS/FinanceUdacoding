package com.sumuzu.financeudacoding.config

import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataKategori.ResponseGetDataKategori
import com.sumuzu.financeudacoding.model.getDataKonsumen.ResponseGetDataKonsumen
import com.sumuzu.financeudacoding.model.getDataProduk.ResponseGetDataProduk
import com.sumuzu.financeudacoding.model.getDataSupplier.ResponseGetDataSupplier
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceKonsumen {

    ////////////////Konsumen
    //getData
    @GET("konsumen/getDataKonsumen.php")
    fun getDataKonsumen() : Call<ResponseGetDataKonsumen>

    //getDatabyId
    @GET("konsumen/getDataKonsumen.php?id=")
    fun getDataKonsumenById(@Query("id") id : String) : Call<ResponseGetDataKonsumen>

    //insert
    @FormUrlEncoded
    @POST("konsumen/insertKonsumen.php")
    fun insertData(@Field("nama") nama : String,
                   @Field("no_tlp") no_tlp : String,
                   @Field("alamat") alamat : String
    ) : Call<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("konsumen/updateKonsumen.php")
    fun updateData(@Field("id") id : String,
                   @Field("nama") nama : String,
                   @Field("no_tlp") no_tlp : String,
                   @Field("alamat") alamat : String
    ) : Call<ResponseAction>

    //delete
    @FormUrlEncoded
    @POST("konsumen/deleteKonsumen.php")
    fun deleteData(@Field("id") id : String
    ) : Call<ResponseAction>

}