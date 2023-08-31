package com.example.dementiaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.dementiaapp.databinding.ActivityDocProfileBinding
import com.google.android.material.tabs.TabLayout

class DocProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDocProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDocProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        var tablayout = findViewById<TabLayout>(R.id.tablayout)

        val fragmentAdapter = DocPagerAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(DocAboutFragment(),"About")
        fragmentAdapter.addFragment(DocAddressFragment(),"Address")
        fragmentAdapter.addFragment(DocRatingFragment(),"Reviews")


        viewPager.adapter = fragmentAdapter
        tablayout.setupWithViewPager(viewPager)
    }
}