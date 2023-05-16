package com.example.hireme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileUpdate : AppCompatActivity() {

    private lateinit var updtName: EditText
    private lateinit var updtDisplay: EditText
    private lateinit var updtEmail: EditText
    private lateinit var btnUpdate: Button

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_update)



        updtName = findViewById(R.id.updtBuyName)
        updtDisplay = findViewById(R.id.updtDisplayname)
        updtEmail = findViewById(R.id.updtBuyEmail)
        btnUpdate = findViewById(R.id.btnUpdate)


        setData()


        btnUpdate.setOnClickListener {

            val bName = updtName.text.toString()
            val bDisplay = updtDisplay.text.toString()
            //val bEmail = updtEmail.text.toString()


            val updtMap = mapOf(

                "BuyerName" to bName,
                "Display" to bDisplay,

                )
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            db.collection("Buyer").document(uid).update(updtMap)

            Toast.makeText(this,"Successfully Edited", Toast.LENGTH_SHORT).show()

            val buyIntent = Intent(this, BuyerProfile::class.java)
            startActivity(buyIntent)

        }


    }

    private fun setData(){

        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        val ref =  db.collection("Buyer").document(userID)

        ref.get().addOnSuccessListener {

            if(it != null){

                val name = it.data?.get("BuyerName").toString()
                val display = it.data?.get("Display").toString()
                val email = it.data?.get("Email").toString()

                updtName.setText(name)
                updtDisplay.setText(display)
                updtEmail.setText(email)

            }

        }

            .addOnFailureListener {

                Toast.makeText(this, "Failed !", Toast.LENGTH_SHORT).show()

            }

    }

}