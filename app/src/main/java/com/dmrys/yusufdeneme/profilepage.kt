package com.dmrys.yusufdeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dmrys.yusufdeneme.databinding.ActivityProfilepageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class profilepage : AppCompatActivity() {

    lateinit var binding: ActivityProfilepageBinding
    private lateinit var auth:FirebaseAuth
    var databaseReference:DatabaseReference?=null
    var database:FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfilepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReference=database?.reference!!.child("Kayıtlar")

        var currentUser=auth.currentUser
        binding.etEmailguncelle.setText(currentUser?.email)

        var userReference = databaseReference?.child(currentUser?.uid!!)
        userReference?.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                binding.etadsoyadguncelle.setText(snapshot.child("adisoyadi").value.toString())
                binding.etphoneguncelle.setText(snapshot.child("Telephone").value.toString())

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        //bilgileri güncelleme
        binding.button2.setOnClickListener(){
            var guncelleemail = binding.etEmailguncelle.text.toString().trim()
            currentUser!!.updateEmail(guncelleemail)
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this@profilepage,"E-mailiniz güncellendi",Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this@profilepage,"Mailiniz güncellenemedi",Toast.LENGTH_LONG).show()
                    }
                }
            //Parola güncelleme
            if (binding.etpasswordguncelle.text.toString()==binding.etpasswordcheckguncelle.toString()){
                var parolaGuncelle = binding.etpasswordguncelle.text.toString().trim()
                currentUser!!.updatePassword(parolaGuncelle)
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            Toast.makeText(this@profilepage,"Parolanız güncellendi",Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(this@profilepage,"Parolanız güncellenemedi",Toast.LENGTH_LONG).show()
                        }

                    }
            }


            //ad-soyad güncelleme
            var currentUserDb =
                currentUser?.let { it1 -> databaseReference?.child(it1.uid) }
            currentUserDb?.removeValue()

            currentUserDb?.child("adisoyadi")
                ?.setValue(binding.etadsoyadguncelle.text.toString())
            currentUserDb?.child("Telephone")
                ?.setValue(binding.etphoneguncelle.text.toString())

            currentUserDb?.child("Mail")
                ?.setValue(binding.etEmailguncelle.text.toString())
            currentUserDb?.child("Password")
                ?.setValue(binding.etpasswordguncelle.text.toString())
            Toast.makeText(this@profilepage,"bilgileriniz  güncellendi",Toast.LENGTH_LONG).show()
        }
    }
}