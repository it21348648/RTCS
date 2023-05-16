package com.example.hireme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BuyerRegistration : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtDisplay: EditText
    private lateinit var edtBuyEmail: EditText
    private lateinit var edtBuyPassword: EditText
    private lateinit var btnBuyReg: Button

    private lateinit var auth: FirebaseAuth
    private var BuyerDB = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_registration)

        auth = Firebase.auth

        edtName = findViewById(R.id.edtBuyName)
        edtDisplay = findViewById(R.id.etDisplayname)
        edtBuyEmail = findViewById(R.id.edtBuyEmail)
        edtBuyPassword = findViewById(R.id.edtBuyPassword)
        btnBuyReg = findViewById(R.id.btnBuyRegistation)

        val buyerReg = BuyerValidation()


        btnBuyReg.setOnClickListener {

            val sEmail = edtBuyEmail.text.toString().trim()
            val sPassword = edtBuyPassword.text.toString().trim()
            val sName= edtName.text.toString().trim()
            val sDisplay = edtDisplay.text.toString().trim()


            if(buyerReg.validateData(sName,sDisplay,sEmail,sPassword)) {

                auth.createUserWithEmailAndPassword(sEmail, sPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            //Sign in success
                            auth.currentUser?.sendEmailVerification()
                                ?.addOnSuccessListener {

                                    Toast.makeText(this, "Please Verify Email", Toast.LENGTH_LONG)
                                        .show()
                                    updateUI()

                                    val buyer = hashMapOf(

                                        "Email" to sEmail,
                                        "BuyerName" to sName,
                                        "Display" to sDisplay,
                                        "Password" to sPassword

                                    )

                                    val userID = FirebaseAuth.getInstance().currentUser!!.uid
                                    BuyerDB.collection("Buyer").document(userID).set(buyer)
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                this,
                                                "Buyer Registered",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            edtBuyEmail.text.clear()
                                            edtName.text.clear()
                                            edtName.text.clear()
                                            edtDisplay.text.clear()
                                        }

                                        .addOnFailureListener {
                                            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT)
                                                .show()
                                        }


                                }
                                ?.addOnFailureListener {
                                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                                }

                        } else {

                            //Sign in fails
                            Toast.makeText(
                                baseContext, "Authentication Failed", Toast.LENGTH_SHORT
                            ).show()
                            updateUI()
                        }
                    }
            }
            else{
                Toast.makeText(this, "Fill All The Fields",Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun updateUI(){
        val intent = Intent(this, BuyerLogin::class.java)
        startActivity(intent)
    }
}