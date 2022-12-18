package com.dmrys.yusufdeneme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dmrys.yusufdeneme.databinding.ActivityTotalsiparispageBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class totalsiparispage : AppCompatActivity() {
    private lateinit var binding: ActivityTotalsiparispageBinding
    private lateinit var  db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTotalsiparispageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= Firebase.firestore



    }
    }
