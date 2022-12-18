package com.dmrys.yusufdeneme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmrys.yusufdeneme.databinding.ActivityMenupageBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Tatlilarpage : AppCompatActivity() {
    private lateinit var binding: ActivityMenupageBinding
    private lateinit var  db: FirebaseFirestore

    private lateinit var PostArrayList:ArrayList<Post>
    private lateinit var feedadapter:FeedRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMenupageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= Firebase.firestore

        PostArrayList=ArrayList<Post>()

        getdata()

        binding.recyclerviewmenu.layoutManager= LinearLayoutManager(this)

        feedadapter=FeedRecyclerAdapter(PostArrayList)
        binding.recyclerviewmenu.adapter=feedadapter


    }
    private fun getdata(){
        db.collection("Tatlılar").addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
            }
            else{
                if (value!=null){
                    if (!value.isEmpty){


                        val documents =   value.documents
                        for (document in documents){
                            val Urunad= document.get("Urunad") as String
                            val Fiyat = document.get("Fiyat") as String
                            val downloadUrl= document.get("downloadUrl") as String
                            val Urunicindekiler= document.get("Urunicindekiler") as String
                            val post =Post(Urunad,Fiyat,downloadUrl,Urunicindekiler)
                            PostArrayList.add(post)

                        }
                        feedadapter.notifyDataSetChanged()
                    }
                }
            }


        }
    }
}