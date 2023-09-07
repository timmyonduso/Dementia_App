package com.example.dementiaapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dementiaapp.databinding.FragmentDocRatingBinding

class DocRatingFragment : Fragment() {

    private lateinit var binding:FragmentDocRatingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDocRatingBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment

        binding.button8.setOnClickListener {
            startActivity(Intent(requireActivity(), CalenderActivity::class.java))
        }

        return view
    }

}