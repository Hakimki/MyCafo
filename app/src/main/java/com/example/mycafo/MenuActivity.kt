package com.example.mycafo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MenuActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var btn_tambah_menu : Button
    lateinit var btn_list_menu : Button
    lateinit var btn_back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportActionBar?.hide()

        btn_list_menu = findViewById(R.id.btn_list_menu)
        btn_tambah_menu= findViewById(R.id.btn_tambah_menu)
        btn_back =findViewById(R.id.back)
        btn_back.setOnClickListener(this)
        btn_tambah_menu.setOnClickListener(this)
        btn_list_menu.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.back ->{
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_tambah_menu->{
                val intent = Intent(this,TambahMenu::class.java)
                startActivity(intent)
            }
            R.id.btn_list_menu->{
                val intent = Intent(this, ListMenu::class.java)
                startActivity(intent)
            }
        }
    }


}