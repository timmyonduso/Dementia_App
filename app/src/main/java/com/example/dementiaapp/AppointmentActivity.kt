package com.example.dementiaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.dementiaapp.databinding.ActivityAppointmentBinding
import com.example.dementiaapp.databinding.FragmentSecondScreenBinding
import com.google.android.material.tabs.TabLayout

class AppointmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppointmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        var tablayout = findViewById<TabLayout>(R.id.tablayout)

        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(UpcomingFragment(),"Upcoming")
        fragmentAdapter.addFragment(PendingFragment(),"Pending")
        fragmentAdapter.addFragment(PastFragment(),"Past")
        fragmentAdapter.addFragment(CancelledFragment(),"Cancelled")


        viewPager.adapter = fragmentAdapter
        tablayout.setupWithViewPager(viewPager)
    }
}