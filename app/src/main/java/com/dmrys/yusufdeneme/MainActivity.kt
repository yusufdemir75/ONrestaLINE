package com.dmrys.yusufdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dmrys.yusufdeneme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.girisuyebutton.setOnClickListener(){

            intent= Intent(applicationContext,Uyepage::class.java)
            startActivity(intent)


        }
        binding.restorangirisbutton.setOnClickListener(){
            intent= Intent(applicationContext,Admingirispage::class.java)
            startActivity(intent)
        }

    }
}