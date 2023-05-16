package com.example.hireme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class BuyerLogin : AppCompatActivity() {

    private lateinit var edtBuyEmail: EditText
    private lateinit var edtBuyPassword: EditText
    private lateinit var btnBuyLogin: Button
    private lateinit var btnBuyRegistration: Button

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_login)

        edtBuyEmail = findViewById(R.id.edtBuyEmail)
        edtBuyPassword = findViewById(R.id.edtBuyPassword)
        btnBuyLogin = findViewById(R.id.btnBuyLogin)
        btnBuyRegistration = findViewById(R.id.btnRegistration)

        auth = Firebase.auth

        btnBuyLogin.setOnClickListener {


            var lemail = edtBuyEmail.text.toString().trim()
            var lpassword = edtBuyPassword.text.toString().trim()

            auth.signInWithEmailAndPassword(lemail,lpassword)
                .addOnCompleteListener(this){
                        task->

                    if(task.isSuccessful){

                        val verification = auth.currentUser?.isEmailVerified

                        if(verification == true){
                            val buyer = auth.currentUser
                            val buyerInt = Intent(this, BuyerView::class.java)
                            startActivity(buyerInt)
                            Toast.makeText(this, "Login Successful !", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(baseContext, "Authentication Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }

        btnBuyRegistration.setOnClickListener {
            val intent = Intent(this, BuyerRegistration::class.java)
            startActivity(intent)
        }

    }
}