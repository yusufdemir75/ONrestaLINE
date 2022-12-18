package com.dmrys.yusufdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.dmrys.yusufdeneme.databinding.ActivityUyepageBinding
import com.google.firebase.auth.FirebaseAuth

class Uyepage : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityUyepageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUyepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.girisbutton.setOnClickListener() {

            var loginemail = binding.editTextTextEmailAddress.text.toString()
            var loginpassword = binding.editTextTextPassword.text.toString()
            if (TextUtils.isEmpty(loginemail)) {
                binding.editTextTextEmailAddress.error = "Lütfen E-mail adresini doldurunuz"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(loginpassword)) {
                binding.editTextTextPassword.error = "Lütfen parolunuzu doldurunuz"
                return@setOnClickListener
            }



            auth.signInWithEmailAndPassword(loginemail, loginpassword)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        intent = Intent(applicationContext, UyeGirisPage::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Hatalı giriş yaptınız", Toast.LENGTH_LONG)
                            .show()
                    }
                }

        }

        binding.kayitbutton.setOnClickListener(){
            intent= Intent(applicationContext,Uyekayitpage::class.java)
            startActivity(intent)
        }
        binding.sifreunuttumbutton.setOnClickListener(){
            intent= Intent(applicationContext,Parolasifirlapage::class.java)
            startActivity(intent)
        }
    }
}