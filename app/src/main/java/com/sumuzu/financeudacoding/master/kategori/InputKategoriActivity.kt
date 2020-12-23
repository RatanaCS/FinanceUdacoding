package com.sumuzu.financeudacoding.master.kategori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.config.NetworkModule
import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataKategori.DataItemKategori
import com.sumuzu.financeudacoding.model.getDataProduk.DataItemProduk
import kotlinx.android.synthetic.main.activity_input_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputKategoriActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_kategori)

        val getData = intent.getParcelableExtra<DataItemKategori>("data")

        if(getData != null){

            etKategori.setText(getData.kategori)

            btnSimpan.text = "Update"
        }

        when(btnSimpan.text){
            "Update" ->{
                btnSimpan.setOnClickListener {
                    if(etKategori.text.isNullOrEmpty()){
                        Toast.makeText(applicationContext, "ANama Kategori harus terisi!!", Toast.LENGTH_LONG).show()
                    }else{
                        updateData(getData?.id, etKategori.text.toString())
                    }
                }
            }
            else -> {
                btnSimpan.setOnClickListener {
                    if(etKategori.text.isNullOrEmpty()){
                        Toast.makeText(applicationContext, "Nama Kategori harus terisi!!", Toast.LENGTH_LONG).show()
                    }else{
                        inputData(etKategori.text.toString())
                    }
                }
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }

    }

    private fun updateData(id: String?, kategori: String?) {
        val update = NetworkModule.serviceKategori().updateData(id ?:"",kategori ?:"")
        update.enqueue(object : Callback<ResponseAction> {
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "Data berhasil diubah", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })


    }

    private fun inputData(kategori: String?) {
        val input = NetworkModule.serviceKategori().insertData(kategori ?:"")
        input.enqueue(object : Callback<ResponseAction> {
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "Data berhasil ditambahkan", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }



}