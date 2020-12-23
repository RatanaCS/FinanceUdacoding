package com.sumuzu.financeudacoding.config

import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataProduk.ResponseGetDataProduk
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceProduk {

    ////////////////Produk
    //getData
    @GET("produk/getDataProduk.php")
    fun getDataProduk() : Call<ResponseGetDataProduk>

    //getDatabyId
    @GET("produk/getDataProduk.php?id=")
    fun getDataProdukById(@Query("id") id : String) : Call<ResponseGetDataProduk>

    //insert
    @FormUrlEncoded
    @POST("produk/insertProduk.php")
    fun insertData(@Field("nama") nama : String,
                   @Field("kategori") kategori : String,
                   @Field("harga_beli") harga_beli : String,
                   @Field("harga_jual") harga_jual : String,
                   @Field("satuan") satuan : String
    ) : Call<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("produk/updateProduk.php")
    fun updateData(@Field("id") id : String,
                   @Field("nama") nama : String,
                   @Field("kategori") kategori : String,
                   @Field("harga_beli") harga_beli : String,
                   @Field("harga_jual") harga_jual : String,
                   @Field("satuan") satuan : String
    ) : Call<ResponseAction>

    //delete
    @FormUrlEncoded
    @POST("produk/deleteProduk.php")
    fun deleteData(@Field("id") id : String
    ) : Call<ResponseAction>

}