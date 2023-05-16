package com.example.rtcs.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rtcs.R

class Optionclz : AppCompatActivity() {

    private lateinit var btnFetchData:Button
    private lateinit var btnInsertData:Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gig_options)

        btnFetchData = findViewById(R.id.viewbtn)
        btnInsertData=findViewById(R.id.button15)


        btnInsertData.setOnClickListener {
            val intent = Intent(this@Optionclz, GigInsert::class.java)
            startActivity(intent)
        }

        btnFetchData.setOnClickListener {
            val intent = Intent(this@Optionclz, FetchingActivity::class.java)
            startActivity(intent)
        }





    }
}