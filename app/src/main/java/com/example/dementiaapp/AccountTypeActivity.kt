package com.example.dementiaapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.example.dementiaapp.databinding.ActivityAccountTypeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AccountTypeActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding:ActivityAccountTypeBinding
    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        binding.submitButton.setOnClickListener {
            setacctype()
        }

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE)

        // Check if the account type is already selected
        val savedAccountType = sharedPreferences.getString("UserType", null)
        if (savedAccountType != null) {
            // Account type is already selected, skip the selection screen
            startActivity(Intent(this, GenderAgeActivity::class.java))
            finish()
        } else {
            // Account type is not selected, set up the UI and click listener
            binding.submitButton.setOnClickListener {
                setacctype()
            }
        }

    }

    private fun setacctype(){
        val userId = mAuth!!.currentUser!!.uid
        // Get selected user type from the radio group
        val userTypeRadioGroup: RadioGroup = findViewById(R.id.radioGroup1)
        val selectedUserType = if (userTypeRadioGroup.checkedRadioButtonId == R.id.radioButton1) {
            "Patient"
        } else {
            "Doctor"
        }

        // Store the user's account type in SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("UserType", selectedUserType)
        editor.apply()

        // Create a hashmap to store the user's details
        val userData = hashMapOf(
            "UserType" to selectedUserType
        )

        // Store the user's data in the real-time database
        FirebaseDatabase.getInstance().reference
            .child("Account Type")
            .child(userId)
            .setValue(userData)
            .addOnSuccessListener {
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Data saving failed", Toast.LENGTH_SHORT).show()
            }


        Toast.makeText(this,"User registered successfully",
            Toast.LENGTH_LONG).show()

        startActivity(Intent(this,GenderAgeActivity::class.java))

    }

}