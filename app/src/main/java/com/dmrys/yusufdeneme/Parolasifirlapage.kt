package com.dmrys.yusufdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.dmrys.yusufdeneme.databinding.ActivityParolasifirlapageBinding
import com.google.firebase.auth.FirebaseAuth

class Parolasifirlapage : AppCompatActivity() {
    lateinit var binding:ActivityParolasifirlapageBinding
    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityParolasifirlapageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()

        binding.sifresifirlabutton.setOnClickListener{
            var parolasıfırlamail = binding.editTextTextEmailAddress2.text.toString().trim()
            if(TextUtils.isEmpty((parolasıfırlamail)))
            {
                binding.editTextTextEmailAddress2.error="Lütfen e-mail adresinizi yazınız."
            }
            else
            {
                auth.sendPasswordResetEmail(parolasıfırlamail)
                    .addOnCompleteListener(this){ sifirlama ->
                        if(sifirlama.isSuccessful){
                            binding.textView2.text="e-mail adresinize sıfırlama bağlantısı gönderildi, lütfen kontrol ediniz."
                        }
                        else
                        {
                            binding.textView2.text="Sıfırlama Gerçekleştirilemedi"
                        }
                    }
            }
        }

        //Giriş sayfasına gitmek için
        binding.geridonbutton.setOnClickListener{
            intent= Intent(applicationContext,Uyepage::class.java)
            startActivity(intent)
            finish()
        }

    }
    }
