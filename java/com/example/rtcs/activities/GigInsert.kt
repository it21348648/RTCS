package com.example.rtcs.activities


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rtcs.R
import com.example.rtcs.model.GigModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class GigInsert : AppCompatActivity() {

    private lateinit var btnInsert: Button
    private lateinit var gigTitle: EditText
    private lateinit var gigPrice: EditText
    private lateinit var category: Spinner
    private lateinit var gigDescription: EditText


    private lateinit var dbRef: DatabaseReference

    //    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gig_insert)


        gigTitle = findViewById(R.id.gigtitle2)
        gigPrice = findViewById(R.id.gigprice)
        gigDescription = findViewById(R.id.gigdescription)
        category = findViewById(R.id.spinnerCategory)
        btnInsert = findViewById(R.id.insertBtn)



        dbRef = FirebaseDatabase.getInstance().getReference("GigData")

        btnInsert.setOnClickListener {
            saveGigData()
        }

    }

    private fun saveGigData() {
        val gigTitle1 = gigTitle.text.toString()
        val gigDescription1 = gigDescription.text.toString()
        val gigPrice1 = gigPrice.text.toString()
        val gigCategory1 = category.selectedItem.toString()

        var isFormValid = true

        if (gigTitle1.isEmpty()) {
            gigTitle.error = "Please enter the gig title"
            isFormValid = false
        }

        if (gigDescription1.isEmpty()) {
            gigDescription.error = "Please enter the gig description"
            isFormValid = false
        }

        if (gigPrice1.isEmpty()) {
            gigPrice.error = "Please enter the gig price in dollars"
            isFormValid = false
        }

        if (gigCategory1.isEmpty()) {
            val errorText = category.selectedView as TextView
            errorText.error = "Please select a category"
            isFormValid = false
        }

        if (isFormValid) {
            val gigId = dbRef.push().key!!
            val gig = GigModel(gigId, gigTitle1, gigCategory1, gigDescription1, gigPrice1)

            dbRef.child(gigId).setValue(gig)
                .addOnCompleteListener {
                    Toast.makeText(this, "Your gig created successfully", Toast.LENGTH_LONG).show()

                    gigTitle.text.clear()
                    gigDescription.text.clear()
                    gigPrice.text.clear()
                    category.setSelection(0)

                    val intent = Intent(this@GigInsert, Optionclz::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}