package com.example.codefooapp

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.codefooapp.databinding.ActivityMainBinding
import com.example.codefooapp.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)


        val textView = findViewById<TextView>(binding.parentText.id)
        val articles = tabs.getTabAt(0)
        if (articles != null) {
            articles.text = "\uD83D\uDCDDArticles"
            articles.icon = Drawable.createFromPath("@drawable/ic_ign_entertainment_inc_vector_logo")

        }
        val videos = tabs.getTabAt(1)
        if (videos != null) {
            videos.text = "▶Videos"
        }

        val queue = Volley.newRequestQueue(this)
        val url = "https://ign-apis.herokuapp.com/articles"
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: $response"
            },
            { textView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }
}