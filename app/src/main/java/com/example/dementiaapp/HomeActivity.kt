package com.example.dementiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dementiaapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button4.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.button8.setOnClickListener {
            startActivity(Intent(this, WhyUsActivity::class.java))
        }

        binding.textView11.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
        binding.button5.setOnClickListener {
            startActivity(Intent(this, AppointmentActivity::class.java))
        }
    }
}