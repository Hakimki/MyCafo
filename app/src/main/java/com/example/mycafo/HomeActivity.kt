package com.example.mycafo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser
    lateinit var btn_transaksi: Button
    lateinit var btn_menu: Button
    lateinit var btn_logout: Button
    lateinit var btn_meja: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        btn_meja = findViewById(R.id.btn_meja)
        btn_menu = findViewById(R.id.btn_menu)
        btn_logout = findViewById(R.id.btn_logout)
        btn_transaksi = findViewById(R.id.btn_transaksi)
        btn_meja.setOnClickListener(this)
        btn_transaksi.setOnClickListener(this)
        btn_logout.setOnClickListener(this)
        btn_menu.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_meja ->{
                val intent = Intent(this, MejaActivity::class.java)
                startActivity(intent)
            }

        R.id.btn_transaksi -> {
            val intent = Intent(this, TransaksiActivity::class.java)
            startActivity(intent)
        }
        R.id.btn_menu -> {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        R.id.btn_logout -> {
            auth.signOut()
            Toast.makeText(this, "Logout Berhasil", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        }
    }
}