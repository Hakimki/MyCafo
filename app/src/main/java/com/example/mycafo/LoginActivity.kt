package com.example.mycafo

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers.Main
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {

    private lateinit var btnback_2: ImageView
    private lateinit var txtForgot: TextView
    private lateinit var txtRegister: TextView
    private lateinit var btnLogin: Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        auth = FirebaseAuth.getInstance()
        txtForgot = findViewById(R.id.tv_to_forgotPass)
        btnback_2 = findViewById(R.id.L_img2)
        txtRegister = findViewById(R.id.L_tv_to_register)
        btnLogin = findViewById(R.id.L_btn1)

        btnback_2.setOnClickListener {
            intent = Intent(this, OnBoardActivity::class.java)
            startActivity(intent)
        }
        txtRegister.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        txtForgot.setOnClickListener{
            intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.L_edt_email).text.toString()
            val password = findViewById<EditText>(R.id.L_edt_password).text.toString()


            //Validasi Email
            if (email.isEmpty()) {
                findViewById<EditText>(R.id.L_edt_email).error = "Email Harus Diisi"
                findViewById<EditText>(R.id.L_edt_email).requestFocus()
                return@setOnClickListener
            }
            //validasi Email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                findViewById<EditText>(R.id.L_edt_email).error = "Email Tidak Valid"
                findViewById<EditText>(R.id.L_edt_email).requestFocus()
                return@setOnClickListener
            }

            //validasi Password
            if (password.isEmpty()) {
                findViewById<EditText>(R.id.L_edt_password).error = "Password Harus Diisi"
                findViewById<EditText>(R.id.L_edt_password).requestFocus()
                return@setOnClickListener
            }

            //validasi Panjang Password
            if (password.length < 8) {
                findViewById<EditText>(R.id.L_edt_password).error = "Password Minimal 8 Kata"
                findViewById<EditText>(R.id.L_edt_password).requestFocus()
                return@setOnClickListener
            }



            LoginFirebase(email, password)

        }


    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
