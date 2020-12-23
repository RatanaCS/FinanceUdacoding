package com.sumuzu.financeudacoding.config

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun getRetrofit() : Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

//        //Tetring HP
//        return Retrofit.Builder().baseUrl("http://192.168.43.150/financeudacoding/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()

        //Wifi Kantor
        return Retrofit.Builder().baseUrl("http://192.168.1.38/financeudacoding/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    fun serviceProduk() : ApiServiceProduk = getRetrofit().create(ApiServiceProduk::class.java)
    fun serviceKategori() : ApiServiceKategori = getRetrofit().create(ApiServiceKategori::class.java)
    fun serviceSupplier() : ApiServiceSupplier = getRetrofit().create(ApiServiceSupplier::class.java)
    fun serviceKonsumen() : ApiServiceKonsumen = getRetrofit().create(ApiServiceKonsumen::class.java)
    fun servicePembelian() : ApiServicePembelian = getRetrofit().create(ApiServicePembelian::class.java)


}