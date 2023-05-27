package com.example.mycafo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MejaAdapter (private var mejaList: List<Meja>):

    RecyclerView.Adapter<MejaAdapter.MejaViewHolder>(){



    inner class MejaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvNama: TextView = itemView.findViewById(R.id.tv_nama_meja)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tv_deskripsi_meja)
        val btnUbah: Button = itemView.findViewById(R.id.btn_ubah_meja)
        val btnHapus: Button = itemView.findViewById(R.id.btn_hapus_meja)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MejaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meja_item_row, parent, false)
        return MejaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MejaViewHolder, position: Int) {
        val currentMeja = mejaList[position]

        holder.tvNama.text = currentMeja.nama_meja
        holder.tvDeskripsi.text = currentMeja.deskripsi

        holder.btnUbah.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, UpdateMejaActivity::class.java)
            intent.putExtra("id", currentMeja.id)

            intent.putExtra("nama_meja",currentMeja.nama_meja)
            intent.putExtra("deskripsi", currentMeja.deskripsi)
            context.startActivity(intent)
        }

        holder.btnHapus.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("meja").document(currentMeja.id)
            docRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(
                        holder.itemView.context,
                        "Berhasil Menghapus Meja",
                        Toast.LENGTH_SHORT
                    ).show()


                }
                .addOnFailureListener {
                    Toast.makeText(
                        holder.itemView.context,
                        "Gagal Menghapus Meja",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }


    override fun getItemCount(): Int {
        return mejaList.size
    }

    fun setMejaList(mejaList: List<Meja>) {
        this.mejaList = mejaList
        notifyDataSetChanged()
    }

}

