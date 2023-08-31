package com.example.dementiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dementiaapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private var regName: EditText? = null
    private var regEmail: EditText? = null
    private var regPassword: EditText? = null
    private var mAuth: FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        regName = findViewById(R.id.editTextName)
        regEmail = findViewById(R.id.editTextTextEmailAddress)
        regPassword = findViewById(R.id.editTextText2)


        binding.loginBtn.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        mAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        binding.button.setOnClickListener{
            createUser()
        }

        databaseReference = FirebaseDatabase.getInstance().reference

    }

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            // Show login form
        }
    }

    private fun createUser(){

        val name = regName!!.text.toString().trim()
        val email = regEmail!!.text.toString().trim()
        val password = regPassword!!.text.toString().trim()


        if (name.isEmpty()){
            regName!!.error = "Please provide your full name"
            regName!!.requestFocus()
        } else if (email.isEmpty()){
            regEmail!!.error = "Email cannot be empty"
            regEmail!!.requestFocus()
        }else if(password.isEmpty()){
            regPassword!!.error = "Password cannot be empty"
            regPassword!!.requestFocus()
        }else {
            mAuth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task->
                if (task.isSuccessful){
                    // Get the user's ID
                    val userId = mAuth!!.currentUser!!.uid

                    // Create a hashmap to store the user's details
                    val userData = hashMapOf(
                        "Name" to name,
                        "Email" to email,
                        "Password" to password
                    )

                    // Store the user's data in the real-time database
                    FirebaseDatabase.getInstance().reference
                        .child("Users")
                        .child(userId)
                        .setValue(userData)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Data saving failed", Toast.LENGTH_SHORT).show()
                        }

                    // Store the user's password in a different path
                    FirebaseDatabase.getInstance().reference
                        .child("Passwords")
                        .child(userId)
                        .setValue(password)

                    Toast.makeText(this,"User registered successfully",
                        Toast.LENGTH_LONG).show()

                    startActivity(Intent(this,LoginActivity::class.java))

                }else{
                    Toast.makeText(this,"User registration failed",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}