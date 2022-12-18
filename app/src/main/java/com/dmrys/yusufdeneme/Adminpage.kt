package com.dmrys.yusufdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dmrys.yusufdeneme.databinding.ActivityAdminpageBinding
import com.dmrys.yusufdeneme.databinding.ActivityUyepageBinding

class Adminpage : AppCompatActivity() {
    lateinit var binding: ActivityAdminpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener(){
            intent= Intent(applicationContext,ImageUploadpage::class.java)
            startActivity(intent)
        }
        binding.imageView3.setOnClickListener(){
            intent= Intent(applicationContext,Siparisvermepage::class.java)
            startActivity(intent)
        }

    }
}