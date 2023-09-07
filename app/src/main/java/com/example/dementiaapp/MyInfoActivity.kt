package com.example.dementiaapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.example.dementiaapp.databinding.ActivityMyInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyInfoActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMyInfoBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var dialog: Dialog
    private lateinit var uid:String
    private lateinit var textViewUserName: TextView
    private lateinit var textViewEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logout.setOnClickListener{
            signOut()
        }

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        databaseReference = FirebaseDatabase.getInstance().getReference("Gender/Gender")
        if (uid.isNotEmpty()){
            getUserData()
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("HeightandWeight")
        if (uid.isNotEmpty()){
            getUserData()
        }

        textViewUserName = findViewById(R.id.textView)

// Check if the user is authenticated
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is signed in
            val displayName = currentUser.displayName
            if (displayName != null) {
                // Display the user's name in the TextView
                textViewUserName.text = "$displayName"
            }
        } else {
            // User is not signed in, handle this case as needed
        }

        textViewEmail = findViewById(R.id.button12)
        // Get the user's email and display it in the textViewEmail
        val email = currentUser?.email
        if (email != null) {
            textViewEmail.text = "$email"
        }
        else {
            // User is not signed in, handle this case as needed
        }

    }

    private fun signOut() {
        auth.signOut()
        val intent = Intent(this, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun getUserData() {

        showProgressBar()
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists() && snapshot.getValue(User::class.java) != null) {
                    val gender  = snapshot.child("Gender").value.toString()
                    val height = snapshot.child("Height").value.toString()
                    val weight = snapshot.child("Weight").value.toString()


                    binding.button7.text = gender
                    binding.button15.text = height
                    binding.button17.text = weight


                } else {
                    hideProgressBar()
                    Toast.makeText(this@MyInfoActivity, "Failed to get user profile data", Toast.LENGTH_SHORT).show()
                }
                hideProgressBar()
            }

            override fun onCancelled(error: DatabaseError) {
                hideProgressBar()
                Toast.makeText(this@MyInfoActivity, "Failed to get user profile data", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun showProgressBar(){

        dialog = Dialog(this@MyInfoActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

    }

    private fun hideProgressBar(){

        dialog.dismiss()

    }
}