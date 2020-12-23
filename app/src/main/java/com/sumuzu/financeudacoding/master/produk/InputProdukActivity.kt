package com.sumuzu.financeudacoding.master.produk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.config.NetworkModule
import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataProduk.DataItemProduk
import kotlinx.android.synthetic.main.activity_input_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputProdukActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_produk)

        val getData = intent.getParcelableExtra<DataItemProduk>("data")

        if(getData != null){
            etNamaProduk.setText(getData.nama)
            etKategori.setText(getData.kategori)
            etHBeli.setText(getData.harga_beli)
            etHJual.setText(getData.harga_jual)
            etSatuan.setText(getData.satuan)
            btnSimpan.text = "Update"
        }

        when(btnSimpan.text){
            "Update" ->{
                btnSimpan.setOnClickListener {
                    if(etNamaProduk.text.isNullOrEmpty() || etKategori.text.isNullOrEmpty() || etHBeli.text.isNullOrEmpty() || etHJual.text.isNullOrEmpty() || etSatuan.text.isNullOrEmpty()){
                        Toast.makeText(applicationContext, "Nama, No Hp, Keperluan Anda harus terisi!!", Toast.LENGTH_LONG).show()
                    }else{
                        updateData(getData?.id, etNamaProduk.text.toString(), etKategori.text.toString(), etHBeli.text.toString(), etHJual.text.toString(), etSatuan.text.toString())
                    }
                }
            }
            else -> {
                btnSimpan.setOnClickListener {
                    if(etNamaProduk.text.isNullOrEmpty() || etKategori.text.isNullOrEmpty() || etHBeli.text.isNullOrEmpty() || etHJual.text.isNullOrEmpty() || etSatuan.text.isNullOrEmpty()){
                        Toast.makeText(applicationContext, "Nama, No Hp, Keperluan Anda harus terisi!!", Toast.LENGTH_LONG).show()
                    }else{
                        inputData(etNamaProduk.text.toString(), etKategori.text.toString(), etHBeli.text.toString(), etHJual.text.toString(), etSatuan.text.toString())
                    }
                }
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }

    }

    private fun updateData(id: String?, nama: String?, kategori: String?, hBeli: String?, hJual: String?, satuan: String?) {
        val update = NetworkModule.serviceProduk().updateData(id ?:"", nama ?:"", kategori ?:"", hBeli ?:"0", hJual ?:"0", satuan ?:"pcs")
        update.enqueue(object : Callback<ResponseAction>{
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

    private fun inputData(nama: String?, kategori: String?, hBeli: String?, hJual: String?, satuan: String?) {
        val input = NetworkModule.serviceProduk().insertData(nama ?:"", kategori ?:"", hBeli ?:"0", hJual ?:"0", satuan ?:"pcs")
        input.enqueue(object : Callback<ResponseAction>{
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