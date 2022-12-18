package com.dmrys.yusufdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dmrys.yusufdeneme.databinding.ActivityKaterogilerpageBinding

class Katerogilerpage : AppCompatActivity() {

    private lateinit var binding: ActivityKaterogilerpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityKaterogilerpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.corbaview.setOnClickListener(){
            intent= Intent(applicationContext,menupage::class.java)
            startActivity(intent)
        }
        binding.salataview.setOnClickListener(){
            intent= Intent(applicationContext,Salatalarpage::class.java)
            startActivity(intent)
        }
        binding.anayemekview.setOnClickListener(){
            intent= Intent(applicationContext,Anayemekpage::class.java)
            startActivity(intent)
        }
        binding.tatliview.setOnClickListener(){
            intent= Intent(applicationContext,Tatlilarpage::class.java)
            startActivity(intent)
        }
        binding.sogukicecekview.setOnClickListener(){
            intent= Intent(applicationContext,Sogukiceceklerpage::class.java)
            startActivity(intent)
        }
        binding.sicakicicekview.setOnClickListener(){
            intent= Intent(applicationContext,Sicakiceceklerpage::class.java)
            startActivity(intent)
        }
    }
}