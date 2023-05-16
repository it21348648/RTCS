package com.example.rtcs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rtcs.activities.Optionclz
import com.google.android.material.bottomnavigation.BottomNavigationView

class Services : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.services) // Replace with your layout file name

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_account -> {
                    // Handle Account menu item click
                    val intent = Intent(this@Services, Optionclz::class.java)
                    startActivity(intent)
                    true
                }
//                R.id.menu_notifications -> {
//                    // Handle Notifications menu item click
//                    openNotificationsFragment()
//                    true
//                }
//                R.id.menu_logout -> {
//                    // Handle Logout menu item click
//                    openOptionsFragment()
//                    true
//                }
                else -> false
            }
        }

        // Set the initial fragment
//        openServicesFragment()
    }


    }




