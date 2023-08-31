package com.example.dementiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.dementiaapp.onboarding.ViewPagerFragment


class SplashActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        navController = Navigation.findNavController(this, R.id.mynav) // Replace with your NavHostFragment ID


        navigateToCorrectScreen()
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    private fun navigateToCorrectScreen() {
        if (onBoardingFinished()) {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish() // Optional: finish the splash activity if needed
        } else {
            navController.navigate(R.id.action_splashFragment_to_viewPagerFragment)
            finish() // Optional: finish the splash activity if needed
        }
    }
}