package com.example.rtcs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rtcs.R
import com.example.rtcs.model.GigModel

class GigAdapter (private  val gigList:ArrayList<GigModel>): RecyclerView.Adapter<GigAdapter.ViewHolder>(){

    private lateinit var myListner: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner){
        myListner =clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.gig_list_item,parent,false)
        return ViewHolder(itemView,myListner)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentGig = gigList[position]
        holder.tvGigName.text = currentGig.gigTitle
    }


    override fun getItemCount(): Int {
        return gigList.size
    }

    class ViewHolder( itemView: View,clickListner: onItemClickListner): RecyclerView.ViewHolder(itemView) {
       val tvGigName : TextView = itemView.findViewById(R.id.tvGigName)

        init {
            itemView.setOnClickListener {
                clickListner.onItemClick(adapterPosition)
            }
        }
    }

}

