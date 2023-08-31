package com.example.dementiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dementiaapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding:ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            startActivity(Intent(this, MyInfoActivity::class.java))
        }
        binding.button2.setOnClickListener{
            startActivity(Intent(this, MedicalHistroryActivity::class.java))
        }
        binding.button3.setOnClickListener{
            startActivity(Intent(this, LifestyleActivity::class.java))
        }

    }
}