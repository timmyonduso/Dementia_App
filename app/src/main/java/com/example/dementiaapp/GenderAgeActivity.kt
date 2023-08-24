package com.example.dementiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dementiaapp.databinding.ActivityGenderAgeBinding

class GenderAgeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityGenderAgeBinding
    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenderAgeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInc.setOnClickListener {
            num++

            binding.textviewStart.text = num.toString()
        }

        binding.btnDec.setOnClickListener {
            num--

            binding.textviewStart.text = num.toString()
        }

//        binding.btnReset.setOnClickListener {
//            num = 0
//
//            binding.textviewStart.text = num.toString()
//        }

        binding.submitButton.setOnClickListener {
            startActivity(Intent(this, HeightWeightActivity::class.java))
        }

    }
}