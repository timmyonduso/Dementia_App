package com.example.dementiaapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.dementiaapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener  {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var drawer: DrawerLayout
    private lateinit var auth: FirebaseAuth
    private lateinit var uid:String
    private lateinit var textViewUserName: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button4.setOnClickListener {
            startActivity(Intent(this, AppointmentActivity::class.java))
        }
        binding.button5.setOnClickListener {
            startActivity(Intent(this, DocProfileActivity::class.java))
        }
        binding.button6.setOnClickListener {
            startActivity(Intent(this, ArticleActivity::class.java))
        }

        binding.button7.setOnClickListener {
            startActivity(Intent(this, AppointmentActivity::class.java))
        }
        binding.button73.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

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
                    val intent = Intent(this, ChatActivity::class.java)
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
                    val intent = Intent(this, ArticleActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }


        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        val counties = arrayOf("Personalized Care Services", "In-home Health Care", "Professional Medical Examination", "Complete medical Supply")
        val spinner = findViewById<Spinner>(R.id.county_spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, counties)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedCounty = counties[position]
                val contactDetails = when (selectedCounty) {
                    "Personalized Care Services" -> "Dr. Patel"
                    "In-home Health Care" -> "Dr Mahbed"
                    "Professional Medical Examination" -> "Dr Raheem"
                    "Complete medical Supply" -> "Dr Hicks"
                    else -> "No contact information available"
                }
            // display contactDetails in a TextView or similar widget
                val textView = findViewById<TextView>(R.id.contact_details_text_view)
                textView.text = contactDetails
                textView.setOnClickListener {
                    val phoneNumber = when (selectedCounty) {
                        "1" -> "Dr Hobson"
                        "2" -> "Dr Clarkson"
                        "3" -> "Dr Hicks"
                        "4" -> "Dr Warrick"
                        else -> ""
                    }
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:$phoneNumber")
                    startActivity(intent)

                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // do nothing
            }
        }

        textViewUserName = findViewById(R.id.userName)

        // Check if the user is authenticated
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is signed in
            val displayName = currentUser.displayName
            // Split the full name to get the first name
            val parts = displayName?.split(" ")
            if (parts != null) {
                if (parts.isNotEmpty()) {
                    // Display the user's first name in the TextView
                    textViewUserName.text = "${parts[0]}"
                }
            }
        } else {
            // User is not signed in, handle this case as needed
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