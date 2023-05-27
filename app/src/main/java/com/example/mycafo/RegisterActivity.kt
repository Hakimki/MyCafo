package com.example.mycafo

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var btnback : ImageView
    private lateinit var textLogin : TextView
    private lateinit var btnRegister:Button
    lateinit var auth : FirebaseAuth
    lateinit var database : FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        database = FirebaseDatabase.getInstance()

        auth = FirebaseAuth.getInstance()
        btnback = findViewById(R.id.R_img2)
        textLogin = findViewById(R.id.R_tv_to_login)
        btnRegister = findViewById(R.id.R_btn)

        btnback.setOnClickListener{
            intent = Intent(this, OnBoardActivity::class.java)
            startActivity(intent)
        }
        textLogin.setOnClickListener{
            intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        btnRegister.setOnClickListener{
            val fullName = findViewById<EditText>(R.id.R_edt_fullname).text.toString()
            val email = findViewById<EditText>(R.id.R_edt_email).text.toString()
            val password = findViewById<EditText>(R.id.R_edt_password).text.toString()
            val confPassword = findViewById<EditText>(R.id.R_edt_confirmPassword).text.toString()

            //Validasi Email
            if(email.isEmpty()){
                findViewById<EditText>(R.id.R_edt_email).error = "Email Harus Diisi"
                findViewById<EditText>(R.id.R_edt_email).requestFocus()
                return@setOnClickListener
            }
            //validasi Email tidak sesuai
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                findViewById<EditText>(R.id.R_edt_email).error= "Email Tidak Valid"
                findViewById<EditText>(R.id.R_edt_email).requestFocus()
                return@setOnClickListener
            }

            //validasi Password
            if(password.isEmpty()){
                findViewById<EditText>(R.id.R_edt_password).error= "Password Harus Diisi"
                findViewById<EditText>(R.id.R_edt_password).requestFocus()
                return@setOnClickListener
            }

            //validasi Panjang Password
            if(password.length < 8){
                findViewById<EditText>(R.id.R_edt_password).error = "Password Minimal 8 Kata"
                findViewById<EditText>(R.id.R_edt_password).requestFocus()
                return@setOnClickListener
            }

            //validasi konfirmasi password
            else if (password!= confPassword){
                Toast.makeText(this, "Password Harus Sama", Toast.LENGTH_SHORT).show()
            }

            //validasi Nama Lengkap
            if(fullName.isEmpty()) {
                findViewById<EditText>(R.id.R_edt_fullname).error = "Nama Harus Diisi"
                findViewById<EditText>(R.id.R_edt_fullname).requestFocus()
                return@setOnClickListener

            }
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()




            RegisterFirebase(fullName,email,password)
        }


    }

    private fun RegisterFirebase(fullName: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    val databaseRef = database.reference.child("users").child(auth.currentUser!!.uid)
                    val users : Users = Users(fullName, email, auth.currentUser!!.uid )
                    databaseRef.setValue(users).addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(this,  "${it.exception?.message}", Toast.LENGTH_SHORT).show()

                    }
                }
                }
            }
    }
}