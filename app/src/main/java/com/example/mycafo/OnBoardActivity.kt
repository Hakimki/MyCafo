package com.example.mycafo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.mycafo.databinding.ActivityMainBinding
import com.example.mycafo.databinding.ActivityOnBoardBinding
import org.w3c.dom.Text

class OnBoardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardBinding
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var adapter : ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots : ArrayList<TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        list.add(
            ImageData(
                "https://i.postimg.cc/Dw9fcSMM/logo.jpg"
            )

        )
        list.add(

        ImageData(
            "https://i.postimg.cc/1X0BtVNV/Screenshot-2023-05-16-224043.jpg"
        )
        )

        adapter = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        button1.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun selectedDot(position: Int) {
        for(i in 0 until list.size){
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_on_primary))
            else
                dots[i].setTextColor(ContextCompat.getColor(this,R.color.secondaryColor))
        }

    }

    private fun setIndicator() {
        for(i in 0 until list.size){
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }
}