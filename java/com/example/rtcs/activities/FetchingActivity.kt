package com.example.rtcs.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rtcs.R
import com.example.rtcs.adapters.GigAdapter
import com.example.rtcs.model.GigModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchingActivity : AppCompatActivity() {

    private lateinit var gigRecyclerView: RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var gigList:ArrayList<GigModel>
    private  lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        gigRecyclerView = findViewById(R.id.rvGig)
        gigRecyclerView.layoutManager= LinearLayoutManager(this)
        gigRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById((R.id.tvLoadingData))

        gigList = arrayListOf<GigModel>()

        getGigData()

    }

    private fun getGigData(){
        gigRecyclerView.visibility =View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef =FirebaseDatabase.getInstance().getReference("GigData")

        //getting the data

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                gigList.clear()
                if(snapshot.exists()){
                    for(gigSnap in snapshot.children){
                        val gigData = gigSnap.getValue(GigModel::class.java)
                        gigList.add(gigData!!)
                    }
                    val myAdapter = GigAdapter(gigList)
                    gigRecyclerView.adapter =myAdapter

                    myAdapter.setOnItemClickListner(object :GigAdapter.onItemClickListner{
                        override fun onItemClick(position: Int){
                            val intent = Intent(this@FetchingActivity,GigDetailsActivity::class.java)
                            intent.putExtra("gigId",gigList[position].gigId)
                            intent.putExtra("gigTitle",gigList[position].gigTitle)
                            intent.putExtra("gigCategory",gigList[position].category)
                            intent.putExtra("gigDescription",gigList[position].gigDescription)
                            intent.putExtra("gigPrice",gigList[position].gigPrice)
                            startActivity(intent)
                        }
                    })



                    gigRecyclerView.visibility =View.VISIBLE
                    tvLoadingData.visibility =View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }

}