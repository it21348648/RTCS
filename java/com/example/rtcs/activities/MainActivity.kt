package com.example.rtcs.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rtcs.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById<Button>(R.id.mainbtn1)
        btn.setOnClickListener {
            val intent = Intent(this@MainActivity, Optionclz::class.java)
            startActivity(intent)
            finish()
        }
    }
    }

