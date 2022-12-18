package com.dmrys.yusufdeneme

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dmrys.yusufdeneme.databinding.CardviewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class siparisadapter(val siparislist: ArrayList<siparis> ): RecyclerView.Adapter<siparisadapter.SiparisHolder>(){
    private lateinit var db : FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    var dbreference: DatabaseReference?=null
    var database: FirebaseDatabase?=null

    class SiparisHolder(val binding: CardviewBinding):RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiparisHolder {

        val binding =CardviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SiparisHolder(binding)


    }

    override fun onBindViewHolder(holder: SiparisHolder, position: Int) {

        holder.binding.adettext.text=siparislist.get(position).adet
        holder.binding.urunadtext.text=siparislist.get(position).Urunad
        holder.binding.tutartext.text=siparislist.get(position).tutar



                holder.binding.button.setOnClickListener(){
                    if (holder.binding.checkBox.isChecked){



                 siparislist.removeAt(position)

                  notifyDataSetChanged()
              }


            }
        }


    override fun getItemCount(): Int {

        return siparislist.size
    }


    }



