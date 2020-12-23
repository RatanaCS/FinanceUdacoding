package com.sumuzu.financeudacoding.pembelian

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.adapter.DataPembelianAdapter
import com.sumuzu.financeudacoding.adapter.DataSupplierAdapter
import com.sumuzu.financeudacoding.config.NetworkModule
import com.sumuzu.financeudacoding.master.supplier.InputSupplierActivity
import com.sumuzu.financeudacoding.model.ResponseAction
import com.sumuzu.financeudacoding.model.getDataPembelian.DataItemPembelian
import com.sumuzu.financeudacoding.model.getDataPembelian.ResponseGetDataPembelian
import com.sumuzu.financeudacoding.model.getDataSupplier.DataItemSupplier
import com.sumuzu.financeudacoding.model.getDataSupplier.ResponseGetDataSupplier
import kotlinx.android.synthetic.main.activity_input_pembelian.*
import kotlinx.android.synthetic.main.activity_pembelian.*
import kotlinx.android.synthetic.main.activity_supplier.*
import kotlinx.android.synthetic.main.list_item_pembelian.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PembelianActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembelian)

        supportActionBar!!.title = "Transaksi Pembelian"

        fabAddPembelian.setOnClickListener {
            val intent = Intent(this, InputPembelianActivity::class.java)
            startActivity(intent)
            progressPembelian.visibility = View.VISIBLE
        }

        showData()

    }

    private fun showData() {

        val listPembelian = NetworkModule.servicePembelian().getDataPembelian()
        listPembelian.enqueue(object : Callback<ResponseGetDataPembelian> {
            override fun onResponse(
                call: Call<ResponseGetDataPembelian>,
                response: Response<ResponseGetDataPembelian>
            ) {
                Log.d("response service", response.message())

                if(response.isSuccessful){

                    val item = response.body()?.data
                    tvJumlahTrans.text = item?.size.toString() + " transaksi pembelian"

                    val adapter = DataPembelianAdapter(item, object : DataPembelianAdapter.OnClickListener{
                        override fun detail(item: DataItemPembelian?) {
                            val intent = Intent(applicationContext, InputPembelianActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)
                        }

                        override fun delete(item: DataItemPembelian?) {
                            AlertDialog.Builder(this@PembelianActivity).apply {
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

                    progressPembelian.visibility = View.GONE
                    rvListPembelian.adapter = adapter

                }
            }

            override fun onFailure(call: Call<ResponseGetDataPembelian>, t: Throwable) {
                Log.d("error response service", t.message)

                progressPembelian.visibility = View.GONE

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun deleteData(id: String?) {

        val delete = NetworkModule.servicePembelian().deleteData(id ?:"")
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