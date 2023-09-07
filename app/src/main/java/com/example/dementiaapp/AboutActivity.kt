package com.example.dementiaapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.dementiaapp.databinding.ActivityAboutBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class AboutActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityAboutBinding
    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var drawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //                code for toolbar
        val toolbar = findViewById<ImageView>(R.id.menu)

        toolbar.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }



//Navigation drawer
        drawer = this.findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


        //bottom navbar
        bottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Homie -> {
                    // Handle the "Home" action
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.connect -> {
                    // Handle the action
                    val intent = Intent(this, AppointmentActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.infooo -> {
                    // Handle the "Search" action
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.connect2 -> {
                    // Handle the "Search" action
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.infooo2 -> {
                    // Handle the "Search" action
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

    }
    //     Menu items click listener
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Homie -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.Setto -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.nav_view -> {
                val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                drawerLayout.openDrawer(GravityCompat.START)
            }


            else -> return super.onOptionsItemSelected(item)
        }
        drawer.closeDrawer(GravityCompat.END)
        return true
    }

    //NavDrawer Items click listener
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Homie -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.Setto -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }

        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}