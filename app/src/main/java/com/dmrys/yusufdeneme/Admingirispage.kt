package com.dmrys.yusufdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.dmrys.yusufdeneme.databinding.ActivityAdmingirispageBinding
import com.google.firebase.auth.FirebaseAuth

class Admingirispage : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    lateinit var binding: ActivityAdmingirispageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdmingirispageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.girisbutton2.setOnClickListener() {

            var loginemail = binding.editTextTextEmailAddress3.text.toString()
            var loginpassword = binding.editTextTextPassword2.text.toString()
            if (TextUtils.isEmpty(loginemail)) {
                binding.editTextTextEmailAddress3.error = "Lütfen E-mail adresini doldurunuz"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(loginpassword)) {
                binding.editTextTextPassword2.error = "Lütfen parolunuzu doldurunuz"
                return@setOnClickListener
            }
            if (loginemail=="dmrysf0320@gmail.com"&&loginpassword=="yusuf123"){
                intent=Intent(applicationContext,Adminpage::class.java)
                startActivity(intent)

            }else{
                Toast.makeText(this@Admingirispage,"Yanlış Giriş Yaptınız",Toast.LENGTH_LONG).show()
            }


        }



    }
}