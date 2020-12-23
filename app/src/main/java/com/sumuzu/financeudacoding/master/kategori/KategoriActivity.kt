package com.sumuzu.financeudacoding.master.kategori

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.adapter.DataKategoriAdapter
import com.sumuzu.financeudacoding.adapter.DataProdukAdapter
import com.sumuzu.financeudacoding.config.NetworkModule
import com.sumuzu.financeudacoding.master.produk.InputProdukActivity
import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataKategori.DataItemKategori
import com.sumuzu.financeudacoding.model.getDataKategori.ResponseGetDataKategori
import com.sumuzu.financeudacoding.model.getDataProduk.DataItemProduk
import com.sumuzu.financeudacoding.model.getDataProduk.ResponseGetDataProduk
import kotlinx.android.synthetic.main.activity_kategori.*
import kotlinx.android.synthetic.main.activity_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KategoriActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        fabAddKategori.setOnClickListener {
            val intent = Intent(this, InputKategoriActivity::class.java)
            startActivity(intent)
            progressKategori.visibility = View.VISIBLE
        }

        showData()

    }

    private fun showData() {

        val listKategori = NetworkModule.serviceKategori().getDataKategori()
        listKategori.enqueue(object : Callback<ResponseGetDataKategori> {
            override fun onResponse(
                call: Call<ResponseGetDataKategori>,
                response: Response<ResponseGetDataKategori>
            ) {
                Log.d("response service", response.message())

                if(response.isSuccessful){

                    val item = response.body()?.data

                    val adapter = DataKategoriAdapter(item, object : DataKategoriAdapter.OnClickListener{
                        override fun detail(item: DataItemKategori?) {
                            val intent = Intent(applicationContext, InputKategoriActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)
                        }

                        override fun delete(item: DataItemKategori?) {
                            AlertDialog.Builder(this@KategoriActivity).apply {
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

                    progressKategori.visibility = View.GONE
                    rvListKategori.adapter = adapter

                }
            }

            override fun onFailure(call: Call<ResponseGetDataKategori>, t: Throwable) {
                Log.d("error response service", t.message)

                progressKategori.visibility = View.GONE

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun deleteData(id: String?) {

        val delete = NetworkModule.serviceKategori().deleteData(id ?:"")
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