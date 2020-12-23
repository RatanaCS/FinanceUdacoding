package com.sumuzu.financeudacoding.config

import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataKategori.ResponseGetDataKategori
import com.sumuzu.financeudacoding.model.getDataProduk.ResponseGetDataProduk
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceKategori {

    ////////////////Kategori
    //getData
    @GET("kategori/getDataKategori.php")
    fun getDataKategori() : Call<ResponseGetDataKategori>

    //getDatabyId
    @GET("kategori/getDataKategori.php?id=")
    fun getDataKategoriById(@Query("id") id : String) : Call<ResponseGetDataKategori>

    //insert
    @FormUrlEncoded
    @POST("kategori/insertKategori.php")
    fun insertData(@Field("kategori") kategori : String
    ) : Call<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("kategori/updateKategori.php")
    fun updateData(@Field("id") id : String,
                   @Field("kategori") kategori : String
    ) : Call<ResponseAction>

    //delete
    @FormUrlEncoded
    @POST("kategori/deleteKategori.php")
    fun deleteData(@Field("id") id : String
    ) : Call<ResponseAction>

}