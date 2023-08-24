package com.example.dementiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dementiaapp.databinding.ActivityLoginBinding
import com.example.dementiaapp.databinding.ActivitySignupBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))
        }
        binding.button.setOnClickListener{
            startActivity(Intent(this, AccountTypeActivity::class.java))
        }
    }
}