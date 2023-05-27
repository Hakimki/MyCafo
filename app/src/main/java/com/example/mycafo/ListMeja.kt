package com.example.mycafo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.firestore.FirebaseFirestore

class ListMeja : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var mejaAdapter: MejaAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_meja)
        supportActionBar?.hide()

        recyclerView = findViewById(R.id.rv_meja)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)

        recyclerView.layoutManager = LinearLayoutManager(this)
        mejaAdapter = MejaAdapter(ArrayList())
        recyclerView.adapter = mejaAdapter

        db = FirebaseFirestore.getInstance()

        swipeRefreshLayout.setOnRefreshListener {
            fetchData()
        }

        fetchData()
    }

    private fun fetchData() {
        db.collection("meja")
            .get()
            .addOnSuccessListener { result ->
                val mejaList = ArrayList<Meja>()
                for (document in result) {
                    val meja = document.toObject(Meja::class.java)
                    mejaList.add(meja)
                }
                mejaAdapter.setMejaList(mejaList)
                swipeRefreshLayout.isRefreshing = false
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Gagal mengambil data: $exception", Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
            }
    }
}