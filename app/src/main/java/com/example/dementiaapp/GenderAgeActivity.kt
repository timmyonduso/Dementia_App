package com.example.dementiaapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.dementiaapp.databinding.ActivityGenderAgeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class GenderAgeActivity : AppCompatActivity() {


    private var mAuth: FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var sharedPreferences: SharedPreferences
    private var age: TextView? = null


    private lateinit var binding:ActivityGenderAgeBinding
    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenderAgeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        age = findViewById(R.id.textview_start)

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

        mAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference


        binding.submitButton.setOnClickListener {
            setacctype()
        }

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE)

        // Check if gender and age are already selected
        val savedGender = sharedPreferences.getString("Gender", null)
        val savedAge = sharedPreferences.getString("Age", null)

        if (savedGender != null && savedAge != null) {
            // Gender and age are already selected, skip the entry screens
            startActivity(Intent(this, HeightWeightActivity::class.java))
            finish()
        } else {
            // Gender and age are not selected, set up the UI and click listener
            binding.submitButton.setOnClickListener {
                setacctype()
            }
        }

    }
    private fun setacctype(){
        val userId = mAuth!!.currentUser!!.uid

        val miaka = age!!.text.toString().trim()
        // Get selected user type from the radio group
        val userTypeRadioGroup: RadioGroup = findViewById(R.id.radiogroup)
        val gender = if (userTypeRadioGroup.checkedRadioButtonId == R.id.radioButton1) {
            "Female"
        } else {
            "Male"
        }

        // Store the user's gender and age in SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("Gender", gender)
        editor.putString("Age", miaka)
        editor.apply()

        // Create a hashmap to store the user's details
        val userData = hashMapOf(
            "Gender" to gender,
            "Age" to miaka
        )

        // Store the user's data in the real-time database
        FirebaseDatabase.getInstance().reference
            .child("Gender")
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

        startActivity(Intent(this,HeightWeightActivity::class.java))

    }

}