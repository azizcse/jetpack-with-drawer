package com.techfort.drawernav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.techfort.drawernav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavController.OnNavigatedListener {


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        drawerLayout = binding.drawerLayout

        navController = Navigation.findNavController(this, R.id.garden_nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Set up navigation menu
        binding.navigationView.setupWithNavController(navController)

        navController.addOnNavigatedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.e(TAG, "onSupportNavigateUp()")
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onNavigated(controller: NavController, destination: NavDestination) {
        Log.e(TAG, "onNavigated() ${destination.label}")
        //supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }
}
