package com.example.hireme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BuyerProfile : AppCompatActivity() {

    private lateinit var viewName: TextView
    private lateinit var viewDiplay: TextView
    private lateinit var viewEmail: TextView
    private lateinit var btnDlt: Button
    private lateinit var btnupdt:Button

    private var db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_profile)


        viewName = findViewById(R.id.txtName)
        viewDiplay = findViewById(R.id.txtDisplay)
        viewEmail = findViewById(R.id.txtEmail)
        btnDlt = findViewById(R.id.btnDlt)
        btnupdt = findViewById(R.id.button10)

        auth = Firebase.auth

        val userID = FirebaseAuth.getInstance().currentUser!!.uid

        val ref = db.collection("Buyer").document(userID)
        ref.get()
            .addOnSuccessListener {

                if(it != null){
                    val name = it.data?.get("BuyerName")?.toString()
                    val display = it.data?.get("Display")?.toString()
                    val email = it.data?.get("Email")?.toString()

                    viewEmail.text = email
                    viewName.text = name
                    viewDiplay.text = display
                }

            }

            .addOnFailureListener {
                Toast.makeText(this, "Data Fetching Failed", Toast.LENGTH_LONG).show()
            }


        btnDlt.setOnClickListener {



            val mapDelete = mapOf(
                "BuyerName" to FieldValue.delete(),
                "Email" to FieldValue.delete(),
                "Display" to FieldValue.delete(),
                "Password" to FieldValue.delete()
            )

            db.collection("Buyer").document(userID).update(mapDelete)
                .addOnSuccessListener{
                    val intent = Intent(this, BuyerRegistration::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Deleting Failed", Toast.LENGTH_SHORT).show()
                }


            val user = auth.currentUser
            user?.delete()?.addOnCompleteListener {
                if(it.isSuccessful){

                    Toast.makeText(this,"Account Deleted Successfully",Toast.LENGTH_SHORT)

                }else{
                    Toast.makeText(this, "Error Occured whiile Deleting",Toast.LENGTH_SHORT)
                }
            }

        }

        btnupdt.setOnClickListener {
            val intent = Intent(this, ProfileUpdate::class.java)
            startActivity(intent)
        }
    }
}