package com.sumuzu.financeudacoding.master

import android.content.Intent
import android.os.Bundle
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sumuzu.financeudacoding.R
import com.sumuzu.financeudacoding.master.kategori.KategoriActivity
import com.sumuzu.financeudacoding.master.konsumen.KonsumenActivity
import com.sumuzu.financeudacoding.master.produk.ProdukActivity
import com.sumuzu.financeudacoding.master.supplier.SupplierActivity
import kotlinx.android.synthetic.main.activity_master.*

class MasterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master)

//        supportActionBar!!.setDisplayShowTitleEnabled(true)
////        supportActionBar!!.setTitle("MASTER")

        val toolbar = findViewById(R.id.my_toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        toolbar?.setTitle("Master")
        toolbar?.setSubtitle("menu Master")
        toolbar?.navigationIcon = ContextCompat.
        getDrawable(this,R.drawable.ic_launcher_foreground)

        btnProduk.setOnClickListener {
            val intent = Intent(this, ProdukActivity::class.java)
            startActivity(intent)
        }

        btnKategori.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            startActivity(intent)
        }

        btnSupplier.setOnClickListener {
            val intent = Intent(this, SupplierActivity::class.java)
            startActivity(intent)
        }

        btnKonsumen.setOnClickListener {
            val intent = Intent(this, KonsumenActivity::class.java)
            startActivity(intent)
        }

    }
}