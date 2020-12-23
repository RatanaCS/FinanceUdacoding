package com.sumuzu.financeudacoding.master.produk

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.adapter.DataProdukAdapter
import com.sumuzu.financeudacoding.config.NetworkModule
import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataProduk.DataItemProduk
import com.sumuzu.financeudacoding.model.getDataProduk.ResponseGetDataProduk
import kotlinx.android.synthetic.main.activity_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produk)

        fabAddProduk.setOnClickListener {
            val intent = Intent(this, InputProdukActivity::class.java)
            startActivity(intent)
            progressProduk.visibility = View.VISIBLE
        }

        showData()

    }

    private fun showData() {

        val listProduk = NetworkModule.serviceProduk().getDataProduk()
        listProduk.enqueue(object : Callback<ResponseGetDataProduk>{
            override fun onResponse(
                call: Call<ResponseGetDataProduk>,
                response: Response<ResponseGetDataProduk>
            ) {
                Log.d("response service", response.message())

                if(response.isSuccessful){

                    val item = response.body()?.data

                    val adapter = DataProdukAdapter(item, object : DataProdukAdapter.OnClickListener{
                        override fun detail(item: DataItemProduk?) {
                            val intent = Intent(applicationContext, InputProdukActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)
                        }

                        override fun delete(item: DataItemProduk?) {
                            AlertDialog.Builder(this@ProdukActivity).apply {
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

                    progressProduk.visibility = View.GONE
                    rvListProduk.adapter = adapter

                }
            }

            override fun onFailure(call: Call<ResponseGetDataProduk>, t: Throwable) {
                Log.d("error response service", t.message)

                progressProduk.visibility = View.GONE

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun deleteData(id: String?) {

        val delete = NetworkModule.serviceProduk().deleteData(id ?:"")
        delete.enqueue(object : Callback<ResponseAction>{
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