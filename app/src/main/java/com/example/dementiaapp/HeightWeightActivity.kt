package com.example.dementiaapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.dementiaapp.databinding.ActivityHeightWeightBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HeightWeightActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference
    private var height: EditText? = null
    private var weight: EditText? = null
    private lateinit var sharedPreferences: SharedPreferences


    private lateinit var binding:ActivityHeightWeightBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeightWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        height = findViewById(R.id.editTextNumberDecimal)
        weight = findViewById(R.id.editTextNumberkg)

        mAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        binding.submitButton.setOnClickListener {
            setacctype()
        }

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE)

        // Check if height and weight are already selected
        val savedHeight = sharedPreferences.getString("Height", null)
        val savedWeight = sharedPreferences.getString("Weight", null)

        if (savedHeight != null && savedWeight != null) {
            // Height and weight are already selected, skip the entry screens
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            // Height and weight are not selected, set up the UI and click listener
            binding.submitButton.setOnClickListener {
                setacctype()
            }
        }
    }


    private fun setacctype(){

        val urefu = height!!.text.toString().trim()
        val kilo = weight!!.text.toString().trim()

        if (urefu.isEmpty()){
            height!!.error = "Enter Height"
            height!!.requestFocus()
        } else if (kilo.isEmpty()){
            weight!!.error = "Enter Weight"
            weight!!.requestFocus()
        }else {
                    // Get the user's ID
                    val userId = mAuth!!.currentUser!!.uid

                    // Store the user's height and weight in SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putString("Height", urefu)
                    editor.putString("Weight", kilo)
                    editor.apply()

                    // Create a hashmap to store the user's details
                    val userData = hashMapOf(
                        "Height" to urefu,
                        "Weight" to kilo,
                    )

                    // Store the user's data in the real-time database
                    FirebaseDatabase.getInstance().reference
                        .child("HeightandWeight")
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

                    startActivity(Intent(this,HomeActivity::class.java))

                }
            }
        }
