package com.sumuzu.financeudacoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumuzu.financeudacoding.master.MasterActivity
import com.sumuzu.financeudacoding.pembelian.PembelianActivity
import com.sumuzu.financeudacoding.penjualan.PenjualanActivity
import com.sumuzu.financeudacoding.report.ReportActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMaster.setOnClickListener {
            val intent = Intent(this, MasterActivity::class.java)
            startActivity(intent)
        }

        btnReport.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }

        btnPembelian.setOnClickListener {
            val intent = Intent(this, PembelianActivity::class.java)
            startActivity(intent)
        }

        btnPenjualan.setOnClickListener {
            val intent = Intent(this, PenjualanActivity::class.java)
            startActivity(intent)
        }

    }
}