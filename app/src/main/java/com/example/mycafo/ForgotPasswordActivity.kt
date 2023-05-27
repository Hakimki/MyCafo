package com.example.mycafo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var buttonReset:Button
    lateinit var btnBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        supportActionBar?.hide()

        buttonReset = findViewById(R.id.f_btn1)
        btnBack = findViewById(R.id.f_img3)

        btnBack.setOnClickListener{
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonReset.setOnClickListener{
            val email = findViewById<EditText>(R.id.f_edt1).text.toString().trim()

            //Validasi Email
            if (email.isEmpty()) {
                findViewById<EditText>(R.id.f_edt1).error = "Email Harus Diisi"
                findViewById<EditText>(R.id.f_edt1).requestFocus()
                return@setOnClickListener
            }
            //validasi Email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                findViewById<EditText>(R.id.f_edt1).error = "Email Tidak Valid"
                findViewById<EditText>(R.id.f_edt1).requestFocus()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this, "Cek Email Untuk Reset Password", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, LoginActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }

                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}