package com.sumuzu.financeudacoding.pembelian

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.config.NetworkModule
import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataPembelian.DataItemPembelian
import com.sumuzu.financeudacoding.utils.CalendarUtil
import kotlinx.android.synthetic.main.activity_input_pembelian.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputPembelianActivity : AppCompatActivity() {

    private var datePicker : String =""
    lateinit var spinnerSupplier : Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_pembelian)

        supportActionBar!!.title = "Pembelian"

        /////
        spinnerSupplier = findViewById(R.id.spSupplier)

        val options = arrayOf("option 1", "option 2")

        spinnerSupplier.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)

        spinnerSupplier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                etNamaSupplier.setText(options.get(position))
                Toast.makeText(applicationContext, options.get(position), Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                Toast.makeText(applicationContext, "pilih salah satu Supplier", Toast.LENGTH_SHORT).show()

            }

        }
        ////////

        etIdPembelian.visibility = View.GONE
        etNominal.visibility = View.GONE

        etDate.setEnabled(true)
        etDate.setOnClickListener {
            val calender = CalendarUtil
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                    view, year, month, day ->
                datePicker = "$year-${(month+1)}-$day"
                etDate.setText(datePicker)
            }, calender.year, calender.month, calender.day)
            datePickerDialog.show()
        }

        val getData = intent.getParcelableExtra<DataItemPembelian>("data")

        if(getData != null){

            etIdPembelian.visibility = View.VISIBLE
            etIdPembelian.setText(getData.id)
            etNamaSupplier.setText(getData.nama)
            etNominal.visibility = View.VISIBLE
            etNominal.setText(getData.nominal.toString())
            etDate.setEnabled(false)
            etDate.setText(getData.tgl_transaksi)

            btnSimpan.text = "Update"
        }

        when(btnSimpan.text){
            "Update" ->{
                btnSimpan.setOnClickListener {
                    if(etNamaSupplier.text.isNullOrEmpty()){
                        Toast.makeText(applicationContext, "Nama Supplier harus terisi!!", Toast.LENGTH_LONG).show()
                    }else{
                        updateData(getData?.id, etNamaSupplier.text.toString())
                    }
                }
            }
            else -> {
                btnSimpan.setOnClickListener {
                    if(etNamaSupplier.text.isNullOrEmpty() || etDate.text.isNullOrEmpty()){
                        Toast.makeText(applicationContext, "Nama Supplier dan Tanggal harus terisi!!", Toast.LENGTH_LONG).show()
                    }else{
                        inputData(etNamaSupplier.text.toString(), etDate.text.toString())
                    }
                }
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }

    }

    private fun updateData(id: String?, supplier: String?) {
        val update = NetworkModule.servicePembelian().updateData(id ?:"",supplier ?:"")
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

    private fun inputData(supplier: String?, tgl_transaksi : String?) {
        val input = NetworkModule.servicePembelian().insertData(supplier ?:"", tgl_transaksi ?:"2020-02-02" )
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