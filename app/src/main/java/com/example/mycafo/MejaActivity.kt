package com.example.mycafo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MejaActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btn_back :ImageView
    lateinit var btn_add_meja :Button
    lateinit var btn_list_meja:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meja)
        supportActionBar?.hide()

        btn_back = findViewById(R.id.back)
        btn_add_meja = findViewById(R.id.btn_tambah_meja)
        btn_list_meja = findViewById(R.id.btn_list_meja)
        btn_back.setOnClickListener(this)
        btn_list_meja.setOnClickListener(this)
        btn_add_meja.setOnClickListener(this)




    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back ->{
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_list_meja ->{
                val intent = Intent(this, ListMeja::class.java)
                startActivity(intent)
            }
            R.id.btn_tambah_meja->{
                val intent = Intent(this, TambahMeja::class.java)
                startActivity(intent)
            }
        }
    }
}