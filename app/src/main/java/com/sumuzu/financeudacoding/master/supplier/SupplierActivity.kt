package com.sumuzu.financeudacoding.master.supplier

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.adapter.DataKategoriAdapter
import com.sumuzu.financeudacoding.adapter.DataSupplierAdapter
import com.sumuzu.financeudacoding.config.NetworkModule
import com.sumuzu.financeudacoding.master.kategori.InputKategoriActivity
import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataKategori.DataItemKategori
import com.sumuzu.financeudacoding.model.getDataKategori.ResponseGetDataKategori
import com.sumuzu.financeudacoding.model.getDataSupplier.DataItemSupplier
import com.sumuzu.financeudacoding.model.getDataSupplier.ResponseGetDataSupplier
import kotlinx.android.synthetic.main.activity_kategori.*
import kotlinx.android.synthetic.main.activity_supplier.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SupplierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplier)

        fabAddSupplier.setOnClickListener {
            val intent = Intent(this, InputSupplierActivity::class.java)
            startActivity(intent)
            progressSupplier.visibility = View.VISIBLE
        }

        showData()

    }

    private fun showData() {

        val listSupplier = NetworkModule.serviceSupplier().getDataSupplier()
        listSupplier.enqueue(object : Callback<ResponseGetDataSupplier> {
            override fun onResponse(
                call: Call<ResponseGetDataSupplier>,
                response: Response<ResponseGetDataSupplier>
            ) {
                Log.d("response service", response.message())

                if(response.isSuccessful){

                    val item = response.body()?.data

                    val adapter = DataSupplierAdapter(item, object : DataSupplierAdapter.OnClickListener{
                        override fun detail(item: DataItemSupplier?) {
                            val intent = Intent(applicationContext, InputSupplierActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)
                        }

                        override fun delete(item: DataItemSupplier?) {
                            AlertDialog.Builder(this@SupplierActivity).apply {
                                setTitle("Hapus Data")
                                setMessage("Yakin mau hapus data??")
                                setPositiveButton("Hapus"){dialog, which->
                                    deleteData(item?.id)
                                    dialog.dismiss()
                                }
                                setNegativeButton("Batal"){dialog, which ->
                                    dialog.dismiss()
                                }
                            }.show()
                        }

                    })

                    progressSupplier.visibility = View.GONE
                    rvListSupplier.adapter = adapter

                }
            }

            override fun onFailure(call: Call<ResponseGetDataSupplier>, t: Throwable) {
                Log.d("error response service", t.message)

                progressSupplier.visibility = View.GONE

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun deleteData(id: String?) {

        val delete = NetworkModule.serviceSupplier().deleteData(id ?:"")
        delete.enqueue(object : Callback<ResponseAction> {
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "Data berhasil diHAPUS", Toast.LENGTH_LONG).show()

                    showData()
                }
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

}