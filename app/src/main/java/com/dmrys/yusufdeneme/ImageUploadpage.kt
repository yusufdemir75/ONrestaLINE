package com.dmrys.yusufdeneme

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dmrys.yusufdeneme.databinding.ActivityImageUploadpageBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.sql.Timestamp
import java.util.*

class ImageUploadpage : AppCompatActivity() {



    lateinit var binding: ActivityImageUploadpageBinding
    private lateinit var storage: FirebaseStorage
    private lateinit var firebaseFireStore: FirebaseFirestore
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>


    private var imageuri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageUploadpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseFireStore = Firebase.firestore
        storage = Firebase.storage
        registerLauncher()
        //inItVars()
        registerClickEvents()



    }

    private fun registerLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val intentFromResult = result.data
                    if (intentFromResult != null) {
                        imageuri = intentFromResult.data
                        imageuri.let {
                            binding.imageView.setImageURI(it)
                        }
                    }
                }

            }
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->

                if (result) {
                    val intenttogallery =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intenttogallery)


                } else {

                }
            }
    }

    private fun registerClickEvents() {


            binding.radiogrup.setOnCheckedChangeListener { radioGroup, i ->

                if (i!=null){
                    if (binding.corbabutton.isChecked){
                        val uuıd = UUID.randomUUID()

                        val imageName = "$uuıd.jpg"
                        val reference = storage.reference
                        val imageReference = reference.child("images").child(imageName)
                        if (imageuri != null) {
                            imageReference.putFile(imageuri!!).addOnSuccessListener {
                                val uploadedPictureReference =
                                    storage.reference.child("images").child(imageName)
                                uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                                    val downloadUrl = uri.toString()


                                    val postMap = hashMapOf<String, Any>()
                                    postMap.put("downloadUrl", downloadUrl)
                                    postMap.put("Fiyat",binding.etMenuAd2.text.toString())
                                    postMap.put("Urunad", binding.etMenuAd.text.toString())
                                    postMap.put("Urunicindekiler",binding.eturunicindekiler.text.toString())
                                    postMap.put("date",com.google.firebase.Timestamp.now())


                                            binding.buttonyukle.setOnClickListener(){
                                                firebaseFireStore.collection("Corbalar").add(postMap).addOnCompleteListener() {task->
                                                    if (task.isComplete && task.isSuccessful) {
                                                        Toast.makeText(
                                                            this@ImageUploadpage,
                                                            "Menüye Eklendi",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        finish()
                                                    }

                                                }.addOnFailureListener {
                                                    Toast.makeText(
                                                        this@ImageUploadpage,
                                                        it.localizedMessage,
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }

                                            }
                                            }
                                    .addOnFailureListener {
                                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()

                                }
                            }
                        }

                    }
                    else if (binding.anayemekbutton.isChecked){
                        val uuıd = UUID.randomUUID()

                        val imageName = "$uuıd.jpg"
                        val reference = storage.reference
                        val imageReference = reference.child("images").child(imageName)
                        if (imageuri != null) {
                            imageReference.putFile(imageuri!!).addOnSuccessListener {
                                val uploadedPictureReference =
                                    storage.reference.child("images").child(imageName)
                                uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                                    val downloadUrl = uri.toString()


                                    val postMap = hashMapOf<String, Any>()
                                    postMap.put("downloadUrl", downloadUrl)
                                    postMap.put("Fiyat",binding.etMenuAd2.text.toString())
                                    postMap.put("Urunad", binding.etMenuAd.text.toString())
                                    postMap.put("Urunicindekiler",binding.eturunicindekiler.text.toString())


                                    binding.buttonyukle.setOnClickListener(){
                                        firebaseFireStore.collection("Ana Yemekler").add(postMap).addOnCompleteListener() {task->
                                            if (task.isComplete && task.isSuccessful) {
                                                //back
                                                finish()
                                            }

                                        }.addOnFailureListener {
                                            Toast.makeText(
                                                this@ImageUploadpage,
                                                "it.localizedMessage",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                    }
                                }.addOnFailureListener {
                                    Toast.makeText(this, "xx", Toast.LENGTH_LONG).show()

                                }
                            }
                        }

                    }
                   else if (binding.salatabutton.isChecked){
                        val uuıd = UUID.randomUUID()

                        val imageName = "$uuıd.jpg"
                        val reference = storage.reference
                        val imageReference = reference.child("images").child(imageName)
                        if (imageuri != null) {
                            imageReference.putFile(imageuri!!).addOnSuccessListener {
                                val uploadedPictureReference =
                                    storage.reference.child("images").child(imageName)
                                uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                                    val downloadUrl = uri.toString()


                                    val postMap = hashMapOf<String, Any>()
                                    postMap.put("downloadUrl", downloadUrl)
                                    postMap.put("Fiyat",binding.etMenuAd2.text.toString())
                                    postMap.put("Urunad", binding.etMenuAd.text.toString())
                                    postMap.put("Urunicindekiler",binding.eturunicindekiler.text.toString())


                                    binding.buttonyukle.setOnClickListener(){
                                        firebaseFireStore.collection("Salatalar").add(postMap).addOnCompleteListener() {task->
                                            if (task.isComplete && task.isSuccessful) {
                                                //back
                                                finish()
                                            }

                                        }.addOnFailureListener {
                                            Toast.makeText(
                                                this@ImageUploadpage,
                                                "it.localizedMessage",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                    }
                                }.addOnFailureListener {
                                    Toast.makeText(this, "xx", Toast.LENGTH_LONG).show()

                                }
                            }
                        }

                    }
                    else if (binding.tatlibutton.isChecked){
                        val uuıd = UUID.randomUUID()

                        val imageName = "$uuıd.jpg"
                        val reference = storage.reference
                        val imageReference = reference.child("images").child(imageName)
                        if (imageuri != null) {
                            imageReference.putFile(imageuri!!).addOnSuccessListener {
                                val uploadedPictureReference =
                                    storage.reference.child("images").child(imageName)
                                uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                                    val downloadUrl = uri.toString()


                                    val postMap = hashMapOf<String, Any>()
                                    postMap.put("downloadUrl", downloadUrl)
                                    postMap.put("Fiyat",binding.etMenuAd2.text.toString())
                                    postMap.put("Urunad", binding.etMenuAd.text.toString())
                                    postMap.put("Urunicindekiler",binding.eturunicindekiler.text.toString())


                                    binding.buttonyukle.setOnClickListener(){
                                        firebaseFireStore.collection("Tatlılar").add(postMap).addOnCompleteListener() {task->
                                            if (task.isComplete && task.isSuccessful) {
                                                //back
                                                finish()
                                            }

                                        }.addOnFailureListener {
                                            Toast.makeText(
                                                this@ImageUploadpage,
                                                "it.localizedMessage",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                    }
                                }.addOnFailureListener {
                                    Toast.makeText(this, "xx", Toast.LENGTH_LONG).show()

                                }
                            }
                        }

                    }
                    else if (binding.sogukicecekbutton.isChecked){
                        val uuıd = UUID.randomUUID()

                        val imageName = "$uuıd.jpg"
                        val reference = storage.reference
                        val imageReference = reference.child("images").child(imageName)
                        if (imageuri != null) {
                            imageReference.putFile(imageuri!!).addOnSuccessListener {
                                val uploadedPictureReference =
                                    storage.reference.child("images").child(imageName)
                                uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                                    val downloadUrl = uri.toString()


                                    val postMap = hashMapOf<String, Any>()
                                    postMap.put("downloadUrl", downloadUrl)
                                    postMap.put("Fiyat",binding.etMenuAd2.text.toString())
                                    postMap.put("Urunad", binding.etMenuAd.text.toString())
                                    postMap.put("Urunicindekiler",binding.eturunicindekiler.text.toString())


                                    binding.buttonyukle.setOnClickListener(){
                                        firebaseFireStore.collection("Sogukicecek").add(postMap).addOnCompleteListener() {task->
                                            if (task.isComplete && task.isSuccessful) {
                                                //back
                                                finish()
                                            }

                                        }.addOnFailureListener {
                                            Toast.makeText(
                                                this@ImageUploadpage,
                                                "it.localizedMessage",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                    }
                                }.addOnFailureListener {
                                    Toast.makeText(this, "xx", Toast.LENGTH_LONG).show()

                                }
                            }
                        }

                    }
                    else if (binding.sCakicecekbutton.isChecked){
                        val uuıd = UUID.randomUUID()

                        val imageName = "$uuıd.jpg"
                        val reference = storage.reference
                        val imageReference = reference.child("images").child(imageName)
                        if (imageuri != null) {
                            imageReference.putFile(imageuri!!).addOnSuccessListener {
                                val uploadedPictureReference =
                                    storage.reference.child("images").child(imageName)
                                uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                                    val downloadUrl = uri.toString()


                                    val postMap = hashMapOf<String, Any>()
                                    postMap.put("downloadUrl", downloadUrl)
                                    postMap.put("Fiyat",binding.etMenuAd2.text.toString())
                                    postMap.put("Urunad", binding.etMenuAd.text.toString())
                                    postMap.put("Urunicindekiler",binding.eturunicindekiler.text.toString())


                                    binding.buttonyukle.setOnClickListener(){
                                        firebaseFireStore.collection("Sıcakicecek").add(postMap).addOnCompleteListener() {task->
                                            if (task.isComplete && task.isSuccessful) {
                                                //back
                                                finish()
                                            }

                                        }.addOnFailureListener {
                                            Toast.makeText(
                                                this@ImageUploadpage,
                                                "it.localizedMessage",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                    }
                                }.addOnFailureListener {
                                    Toast.makeText(this, "xx", Toast.LENGTH_LONG).show()

                                }
                            }
                        }

                    }



                }
else{
    Toast.makeText(this,"Lütfen Seçeneklerden Birini Seçiniz",Toast.LENGTH_LONG).show()
                }

            }




        binding.buttonsec.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    Snackbar.make(
                        it,
                        "Galeriye erişim izni gerekli",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("Give Permissions") {
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)


                    }.show()

                } else {

                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }


            } else {
                val intenttogallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intenttogallery)
            }
        }


    }

    }