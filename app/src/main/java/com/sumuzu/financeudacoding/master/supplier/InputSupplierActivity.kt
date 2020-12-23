package com.sumuzu.financeudacoding.master.supplier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.config.NetworkModule
import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataSupplier.DataItemSupplier
import kotlinx.android.synthetic.main.activity_input_supplier.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputSupplierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_supplier)

        val getData = intent.getParcelableExtra<DataItemSupplier>("data")

        if(getData != null){

            etNamaSupplier.setText(getData.nama)
            etNoTlp.setText(getData.no_tlp)
            etAlamat.setText(getData.alamat)

            btnSimpan.text = "Update"
        }

        when(btnSimpan.text){
            "Update" ->{
                btnSimpan.setOnClickListener {
                    if(etNamaSupplier.text.isNullOrEmpty() || etNoTlp.text.isNullOrEmpty() || etAlamat.text.isNullOrEmpty()){
                        Toast.makeText(applicationContext, "Nama, No Tlp dan Alamat harus terisi!!", Toast.LENGTH_LONG).show()
                    }else{
                        updateData(getData?.id, etNamaSupplier.text.toString(), etNoTlp.text.toString(), etAlamat.text.toString())
                    }
                }
            }
            else -> {
                btnSimpan.setOnClickListener {
                    if(etNamaSupplier.text.isNullOrEmpty() || etNoTlp.text.isNullOrEmpty() || etAlamat.text.isNullOrEmpty()){
                        Toast.makeText(applicationContext, "Nama, No Tlp dan Alamat harus terisi!!", Toast.LENGTH_LONG).show()
                    }else{
                        inputData(etNamaSupplier.text.toString(), etNoTlp.text.toString(), etAlamat.text.toString())
                    }
                }
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }

    }

    private fun updateData(id: String?, nama: String?, no_tlp: String?, alamat : String?) {
        val update = NetworkModule.serviceSupplier().updateData(id ?:"",nama ?:"", no_tlp ?:"", alamat ?:"")
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

    private fun inputData(nama: String?, no_tlp: String?, alamat : String?) {
        val input = NetworkModule.serviceSupplier().insertData(nama ?:"", no_tlp ?:"", alamat ?:"")
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