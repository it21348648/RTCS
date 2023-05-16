package com.example.rtcs.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rtcs.R
import com.example.rtcs.model.GigModel
import com.google.firebase.database.FirebaseDatabase

class GigDetailsActivity: AppCompatActivity () {

    private lateinit var tvGigId: TextView
    private lateinit var tvGigTitle: TextView
    private lateinit var tvGigCategory: TextView
    private lateinit var tvGigDescription: TextView
    private lateinit var tvGigPrice: TextView
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_gig)

        initView()
        setValuesToViews()

        btnEdit.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("gigId").toString(),
                intent.getStringExtra("gigTitle").toString()

            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("gigId").toString()
            )
        }
    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("GigData").child(id)
        val mdelete = dbRef.removeValue()

        mdelete.addOnSuccessListener {
            Toast.makeText(this,"Gig data deleted successfully",Toast.LENGTH_LONG).show()
            val intent = Intent(this,Optionclz::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this,"Deleting Err ${error.message}",Toast.LENGTH_LONG).show()

        }
    }

    private fun initView() {
        tvGigId=findViewById(R.id.gigId)
        tvGigTitle =findViewById(R.id.textViewtitle)
        tvGigCategory=findViewById(R.id.textViewcategory)
        tvGigDescription=findViewById(R.id.textViewdescription)
        tvGigPrice=findViewById(R.id.textViewprice)
        btnEdit=findViewById(R.id.buttonUpd)
        btnDelete=findViewById(R.id.buttondelete)
    }

    private fun setValuesToViews()
    {

        tvGigId.text = intent.getStringExtra("gigId")
        tvGigTitle.text =intent.getStringExtra("gigTitle")
        tvGigCategory.text =intent.getStringExtra("gigCategory")
        tvGigDescription.text =intent.getStringExtra("gigDescription")
        tvGigPrice.text =intent.getStringExtra("gigPrice")


    }

    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(
        gigId:String,
        gigTitle:String
    ){
        val myDialog =AlertDialog.Builder(this)
        val inflater =layoutInflater
        val myDialogView = inflater.inflate(R.layout.update_gig,null)

        myDialog.setView(myDialogView)

        val gigTitle = myDialogView.findViewById<EditText>(R.id.textViewtitle)
        val gigCategory = myDialogView.findViewById<EditText>(R.id.textViewcategory)
        val gigDescription = myDialogView.findViewById<EditText>(R.id.textViewdescription)
        val gigPrice = myDialogView.findViewById<EditText>(R.id.textViewprice)
        val btnUpdate = myDialogView.findViewById<Button>(R.id.buttonuupdate)

        gigTitle.setText(intent.getStringExtra("gigTitle").toString())
        gigCategory.setText(intent.getStringExtra("gigCategory").toString())
        gigDescription.setText(intent.getStringExtra("gigDescription").toString())
        gigPrice.setText(intent.getStringExtra("gigPrice").toString())

        myDialog.setTitle("Update your details here")

        val alertDialog = myDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener {
            updateGigData(
                gigId,
                gigTitle.text.toString(),
                gigCategory.text.toString(),
                gigDescription.text.toString(),
                gigPrice.text.toString()
            )
            Toast.makeText(applicationContext,"Gig details updated successfully !",Toast.LENGTH_LONG).show()
            // we are setting updated data to text views
            tvGigTitle.text = gigTitle.text.toString()
            tvGigCategory.text =gigCategory.text.toString()
            tvGigDescription.text =gigDescription.text.toString()
            tvGigPrice.text =gigPrice.text.toString()

            alertDialog.dismiss()
        }




    }

    private fun updateGigData(
        id:String,
        title:String,
        category: String,
        description:String,
        price:String

    ){
        val dbRef =FirebaseDatabase.getInstance().getReference("GigData").child(id)
        val gigInfo = GigModel(id,title,category,description,price)

        dbRef.setValue(gigInfo)

    }



}