package com.example.dementiaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.dementiaapp.databinding.FragmentDocAddressBinding
import com.example.dementiaapp.databinding.FragmentUpcoming2Binding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UpcomingFragment : Fragment() {

    private lateinit var binding: FragmentUpcoming2Binding
    private lateinit var dateTextView: TextView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpcoming2Binding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }
}
