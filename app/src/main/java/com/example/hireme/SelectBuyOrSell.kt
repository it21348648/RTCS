package com.example.hireme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectBuyOrSell : AppCompatActivity() {

    private lateinit var btnSell: Button
    private lateinit var btnBuy: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_buy_or_sell)

        btnSell = findViewById(R.id.btnSell)
        btnBuy = findViewById(R.id.btnBuy)



        btnSell.setOnClickListener {
            val sellIntent = Intent(this, SellerLogin::class.java)
            startActivity(sellIntent)
        }

        btnBuy.setOnClickListener {
            val buyIntent = Intent(this, BuyerLogin::class.java)
            startActivity(buyIntent)
        }

    }
}