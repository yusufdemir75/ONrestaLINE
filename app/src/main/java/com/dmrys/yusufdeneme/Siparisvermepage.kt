package com.dmrys.yusufdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmrys.yusufdeneme.databinding.ActivityMenupageBinding
import com.dmrys.yusufdeneme.databinding.ActivitySiparisvermepageBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Siparisvermepage : AppCompatActivity() {
    private lateinit var binding: ActivitySiparisvermepageBinding
    private lateinit var  db: FirebaseFirestore

    private lateinit var PostArrayList:ArrayList<siparis>
    private lateinit var feedadapter:siparisadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySiparisvermepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= Firebase.firestore

        PostArrayList=ArrayList<siparis>()

        getdata()

        binding.siparisrecycler.layoutManager= LinearLayoutManager(this)

        feedadapter= siparisadapter(PostArrayList)
        binding.siparisrecycler.adapter=feedadapter




    }

    private fun getdata() {
        db.collection("Siparisler").orderBy("date",Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
            }
            else{
                if (value!=null){
                    if (!value.isEmpty){


                        val documents =   value.documents
                        for (document in documents){

                            val Urunad= document.get("Urunad") as String
                            val fiyat = document.get("fiyat") as String
                            val adet= document.get("adet") as String
                            val tutar= document.get("tutar") as String


                            val siparis =siparis(Urunad,adet, fiyat,tutar)
                            PostArrayList.add(siparis)


                        }
                        feedadapter.notifyDataSetChanged()
                    }
                }
            }



        }
    }


}