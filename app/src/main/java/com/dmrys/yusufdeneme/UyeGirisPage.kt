package com.dmrys.yusufdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dmrys.yusufdeneme.databinding.ActivityUyeGirisPageBinding
import com.dmrys.yusufdeneme.databinding.ActivityUyekayitpageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class UyeGirisPage : AppCompatActivity() {

    lateinit var binding: ActivityUyeGirisPageBinding
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference?=null
    var database: FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUyeGirisPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReference=database?.reference!!.child("Kayıtlar")
        //Authenticationa kayıtlı mail adresini alma
        var currentUser=auth.currentUser
        binding.mailtext.text=currentUser?.email
        //realtime databasedeki verileri alma
        var userReference=databaseReference?.child((currentUser?.uid!!))
        userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.adsoyadtext.text = snapshot.child("adisoyadi").value.toString()
                binding.phonetext.text = snapshot.child("Telephone").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        //hesaptan çıkış yapma işlemini gerçekleştirme
        binding.cikisbutton.setOnClickListener(){
            auth.signOut()
            startActivity(Intent(this@UyeGirisPage,Uyepage::class.java))
            finish()
        }
//hesabı silme işlemini gerçekleştirme
        binding.hesapsilbutton.setOnClickListener(){
            currentUser?.delete()?.addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(applicationContext,"Hesabınız Silindi", Toast.LENGTH_LONG).show()
                    auth.signOut()
                    startActivity(Intent(this@UyeGirisPage,Uyepage::class.java))
                    finish()
                    var currentUserDb =
                        currentUser?.let { it1 -> databaseReference?.child(it1.uid) }
                    currentUserDb?.removeValue()
                }
            }

        }
        binding.menuimage.setOnClickListener(){
            intent=Intent(applicationContext,Katerogilerpage::class.java)
            startActivity(intent)
        }
        binding.profileimage.setOnClickListener(){
            intent=Intent(applicationContext,profilepage::class.java)
            startActivity(intent)
        }



    }

}