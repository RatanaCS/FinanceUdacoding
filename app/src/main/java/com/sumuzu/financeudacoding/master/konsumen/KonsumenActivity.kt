package com.sumuzu.financeudacoding.master.konsumen

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.adapter.DataKonsumenAdapter
import com.sumuzu.financeudacoding.adapter.DataSupplierAdapter
import com.sumuzu.financeudacoding.config.NetworkModule
import com.sumuzu.financeudacoding.master.supplier.InputSupplierActivity
import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataKonsumen.DataItemKonsumen
import com.sumuzu.financeudacoding.model.getDataKonsumen.ResponseGetDataKonsumen
import com.sumuzu.financeudacoding.model.getDataSupplier.DataItemSupplier
import com.sumuzu.financeudacoding.model.getDataSupplier.ResponseGetDataSupplier
import kotlinx.android.synthetic.main.activity_konsumen.*
import kotlinx.android.synthetic.main.activity_supplier.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KonsumenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konsumen)

        fabAddKonsumen.setOnClickListener {
            val intent = Intent(this, InputKonsumenActivity::class.java)
            startActivity(intent)
            progressKonsumen.visibility = View.VISIBLE
        }

        showData()

    }

    private fun showData() {

        val listKonsumen = NetworkModule.serviceKonsumen().getDataKonsumen()
        listKonsumen.enqueue(object : Callback<ResponseGetDataKonsumen> {
            override fun onResponse(
                call: Call<ResponseGetDataKonsumen>,
                response: Response<ResponseGetDataKonsumen>
            ) {
                Log.d("response service", response.message())

                if(response.isSuccessful){

                    val item = response.body()?.data

                    val adapter = DataKonsumenAdapter(item, object : DataKonsumenAdapter.OnClickListener{
                        override fun detail(item: DataItemKonsumen?) {
                            val intent = Intent(applicationContext, InputKonsumenActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)
                        }

                        override fun delete(item: DataItemKonsumen?) {
                            AlertDialog.Builder(this@KonsumenActivity).apply {
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

                    progressKonsumen.visibility = View.GONE
                    rvListKonsumen.adapter = adapter

                }
            }

            override fun onFailure(call: Call<ResponseGetDataKonsumen>, t: Throwable) {
                Log.d("error response service", t.message)

                progressSupplier.visibility = View.GONE

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun deleteData(id: String?) {

        val delete = NetworkModule.serviceKonsumen().deleteData(id ?:"")
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