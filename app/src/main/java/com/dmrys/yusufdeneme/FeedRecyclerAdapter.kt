package com.dmrys.yusufdeneme

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmrys.yusufdeneme.databinding.ActivityTotalsiparispageBinding
import com.dmrys.yusufdeneme.databinding.CardviewBinding
import com.dmrys.yusufdeneme.databinding.RecyclerRowBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class FeedRecyclerAdapter(val postList:ArrayList<Post>): RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder>() {
    private lateinit var db : FirebaseFirestore


    class PostHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        db = Firebase.firestore
        val binding =RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        holder.binding.fiyattext.text=postList.get(position).Fiyat
        Picasso.get().load(postList.get(position).downloadUrl).into(holder.binding.imageView10)
        holder.binding.textView10.text=postList.get(position).Urunad

        val intent=Intent(holder.itemView.context,detaipage::class.java)

        var Fiyat:String?=postList.get(position).Fiyat
        var Urunicerigi:String?=postList.get(position).Urunicindekiler
        var Urunad:String?=postList.get(position).Urunad

        intent.putExtra("Fiyat",Fiyat)
        intent.putExtra("Urunicerigi",Urunicerigi)
        intent.putExtra("Urunad",Urunad)



        holder.itemView.setOnClickListener(){
            holder.itemView.context.startActivity(intent)
        }


    }



    override fun getItemCount(): Int {

        return postList.size
    }
}