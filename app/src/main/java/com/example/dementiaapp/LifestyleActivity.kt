package com.example.dementiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dementiaapp.databinding.ActivityLifestyleBinding
import com.google.firebase.auth.FirebaseAuth

class LifestyleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifestyleBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifestyleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.logout.setOnClickListener{
            signOut()
        }
    }
    private fun signOut() {
        auth.signOut()
        val intent = Intent(this, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }


}