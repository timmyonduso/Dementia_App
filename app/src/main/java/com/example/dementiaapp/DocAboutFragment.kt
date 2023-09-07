package com.example.dementiaapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dementiaapp.databinding.FragmentDocAboutBinding

class DocAboutFragment : Fragment() {

    private lateinit var binding: FragmentDocAboutBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDocAboutBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment

        binding.button8.setOnClickListener {
            startActivity(Intent(requireActivity(), ChatActivity::class.java))
        }

        return view
    }

}