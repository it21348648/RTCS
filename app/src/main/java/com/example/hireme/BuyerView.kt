package com.example.hireme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class BuyerView : AppCompatActivity() {

    private lateinit var btnProfile:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_view)

        btnProfile = findViewById(R.id.btnProfile)

        btnProfile.setOnClickListener {

            val proInt = Intent(this, BuyerProfile::class.java)
            startActivity(proInt)

        }

    }
}