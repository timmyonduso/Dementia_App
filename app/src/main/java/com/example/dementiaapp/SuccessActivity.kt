package com.example.dementiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dementiaapp.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}