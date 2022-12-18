package com.dmrys.yusufdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.dmrys.yusufdeneme.databinding.ActivityUyekayitpageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Uyekayitpage : AppCompatActivity() {
lateinit var binding: ActivityUyekayitpageBinding
lateinit var auth:FirebaseAuth
var dbreference: DatabaseReference?=null
    var db:FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding = ActivityUyekayitpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        db= FirebaseDatabase.getInstance()
        dbreference=db?.reference!!.child("Kayıtlar")
        var currentUser = auth.currentUser


//Kaydet butonu ile yeni kullanıcı kayıt etme.
        binding.button2.setOnClickListener{
            var uyeIsimSoyisim=binding.etadsoyadguncelle.text.toString()
            var uyeMail=binding.etEmailguncelle.text.toString()
            var uyeSifre=binding.etpasswordguncelle.text.toString()
            var uyesifrekontrol = binding.etpasswordcheckguncelle.text.toString()
            if(TextUtils.isEmpty(uyeIsimSoyisim))
            {
                binding.etadsoyadguncelle.error="Lütfen isim ve soyisminizi giriniz"
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(uyeMail))
            {
                binding.etEmailguncelle.error="Lütfen mail adresinizi giriniz"
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(uyeSifre))
            {
                binding.etpasswordguncelle.error="Lütfen şifrenizi giriniz"
                return@setOnClickListener
            }

            if (uyeSifre==uyesifrekontrol) {
                //Email,parola ve kullanıcı bilgilerini veri tabanına ekleme.
                auth.createUserWithEmailAndPassword(
                    binding.etEmailguncelle.text.toString(),
                    binding.etpasswordguncelle.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            //Şuanki kullanıcı bilgilerini alalım.


                            //Kullanıcı id'sini alıp o id adı altında adımızı ve soyadımızı kaydedelim.
                            var currentUserDb =
                                currentUser?.let { it1 -> dbreference?.child(it1.uid) }
                            currentUserDb?.child("adisoyadi")
                                ?.setValue(binding.etadsoyadguncelle.text.toString())
                            currentUserDb?.child("Mail")?.setValue(binding.etEmailguncelle.text.toString())
                            currentUserDb?.child("Telephone")
                                ?.setValue(binding.etphoneguncelle.text.toString())
                            currentUserDb?.child("Password")
                                ?.setValue(binding.etpasswordguncelle.text.toString())
                            Toast.makeText(this@Uyekayitpage, "Kayıt Başarılı", Toast.LENGTH_LONG)
                                .show()

                            intent = Intent(applicationContext, Uyepage::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this@Uyekayitpage, "Kayıt Hatalı", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            }
            else{
                binding.etpasswordcheckguncelle.error="Şifreleriniz benzer değil"
                return@setOnClickListener
            }

        }


        }


    }
