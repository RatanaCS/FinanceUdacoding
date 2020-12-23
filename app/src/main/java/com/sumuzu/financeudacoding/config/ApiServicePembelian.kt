package com.sumuzu.financeudacoding.config

import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataKategori.ResponseGetDataKategori
import com.sumuzu.financeudacoding.model.getDataPembelian.ResponseGetDataPembelian
import com.sumuzu.financeudacoding.model.getDataProduk.ResponseGetDataProduk
import retrofit2.Call
import retrofit2.http.*

interface ApiServicePembelian {

    ////////////////Pembelian
    //getData
    @GET("pembelian/getDataPembelian.php")
    fun getDataPembelian() : Call<ResponseGetDataPembelian>

    //getDatabyId
    @GET("pembelian/getDataPembelian.php?id=")
    fun getDataPembelianById(@Query("id") id : String) : Call<ResponseGetDataPembelian>

    //insert
    @FormUrlEncoded
    @POST("pembelian/insertPembelian.php")
    fun insertData(@Field("supplier") supplier : String,
                   @Field("tgl_transaksi") tgl_transaksi : String
    ) : Call<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("pembelian/updatePembelian.php")
    fun updateData(@Field("id") id : String,
                   @Field("supplier") supplier : String
    ) : Call<ResponseAction>

    //delete
    @FormUrlEncoded
    @POST("pembelian/deletePembelian.php")
    fun deleteData(@Field("id") id : String
    ) : Call<ResponseAction>

}