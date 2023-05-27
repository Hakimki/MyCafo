package com.example.mycafo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class UpdateMejaActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var etNama: EditText
    private lateinit var etDeskripsi: EditText
    private lateinit var btnUpdate: Button
    private var idMeja: String? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_meja)
        supportActionBar?.hide()

        db = FirebaseFirestore.getInstance()

        etNama = findViewById(R.id.editTextNama)
        etDeskripsi = findViewById(R.id.editTextDeskripsi)
        btnUpdate = findViewById(R.id.buttonUpdate_meja)


        idMeja = intent.getStringExtra("id")
        val mejaName = intent.getStringExtra("nama_meja")
        val mejaDescription = intent.getStringExtra("deskripsi")

        // Mengisi nilai awal pada EditText dengan data menu yang dikirimkan
        etNama.setText(mejaName)
        etDeskripsi.setText(mejaDescription)




        btnUpdate.setOnClickListener {
            // Mendapatkan data dari EditText
            val namaMejaBaru = etNama.text.toString().trim()
            val deskripsi = etDeskripsi.text.toString().trim()

            if (idMeja != null) {
                // Memperbarui data menu di Firebase Firestore
                db.collection("meja")
                    .document(idMeja!!)
                    .update(
                        mapOf(
                            "nama_meja" to namaMejaBaru,
                            "deskripsi" to deskripsi
                        )
                    )
                    .addOnSuccessListener {
                        Toast.makeText(this, "Meja berhasil diperbarui", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Meja gagal diperbarui", Toast.LENGTH_SHORT).show()
                    }


        }
    }


    }


    }




