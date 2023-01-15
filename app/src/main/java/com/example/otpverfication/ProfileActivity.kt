package com.example.otpverfication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.otpverfication.databinding.ActivityProfileBinding
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class ProfileActivity : AppCompatActivity() {


    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var  databse:FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    private lateinit var selectedImage:Uri
    private lateinit var dialog: AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog=AlertDialog.Builder(this).setMessage("Updating Profile")
            .setCancelable(false)

        databse= FirebaseDatabase.getInstance()
        storage=FirebaseStorage.getInstance()
        binding.circleImageView.setOnClickListener{
            val intent=Intent()
            intent.action=Intent.ACTION_GET_CONTENT
            intent.type="Image/*"
            startActivityForResult(intent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(data!=null){
            if(data.data!=null){
                selectedImage=data.data!!
                binding.circleImageView.setImageURI(selectedImage)
            }
        }
    }
}