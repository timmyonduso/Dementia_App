package com.example.dementiaapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dementiaapp.databinding.FragmentDocAddressBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DocAddressFragment : Fragment() , OnMapReadyCallback {

    private lateinit var binding: FragmentDocAddressBinding
    private lateinit var mMap: GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDocAddressBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.button8.setOnClickListener {
            startActivity(Intent(requireActivity(), CalenderActivity::class.java))
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(33.748995, -84.387982)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Dr Abinhav Pin")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
