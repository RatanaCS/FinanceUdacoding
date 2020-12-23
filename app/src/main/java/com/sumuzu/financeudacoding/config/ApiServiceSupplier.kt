package com.sumuzu.financeudacoding.config

import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataKategori.ResponseGetDataKategori
import com.sumuzu.financeudacoding.model.getDataProduk.ResponseGetDataProduk
import com.sumuzu.financeudacoding.model.getDataSupplier.ResponseGetDataSupplier
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceSupplier {

    ////////////////Supplier
    //getData
    @GET("supplier/getDataSupplier.php")
    fun getDataSupplier() : Call<ResponseGetDataSupplier>

    //getDatabyId
    @GET("supplier/getDataSupplier.php?id=")
    fun getDataSupplierById(@Query("id") id : String) : Call<ResponseGetDataSupplier>

    //insert
    @FormUrlEncoded
    @POST("supplier/insertSupplier.php")
    fun insertData(@Field("nama") nama : String,
                   @Field("no_tlp") no_tlp : String,
                   @Field("alamat") alamat : String
    ) : Call<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("supplier/updateSupplier.php")
    fun updateData(@Field("id") id : String,
                   @Field("nama") nama : String,
                   @Field("no_tlp") no_tlp : String,
                   @Field("alamat") alamat : String
    ) : Call<ResponseAction>

    //delete
    @FormUrlEncoded
    @POST("supplier/deleteSupplier.php")
    fun deleteData(@Field("id") id : String
    ) : Call<ResponseAction>

}