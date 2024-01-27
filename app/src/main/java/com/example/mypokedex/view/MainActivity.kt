package com.example.mypokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mypokedex.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findAllElements()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home_fragment,R.id.unknown_pokemon_fragment),
            drawerLayout
        )
        navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    private fun findAllElements(){
        toolbar = findViewById(R.id.nav_toolbar)
        navController = findNavController(R.id.main_nav_host_fragment)
        drawerLayout = findViewById(R.id.main_drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_search -> {
                supportActionBar?.setCustomView(R.layout.custom_search_view)
                supportActionBar?.setDisplayShowCustomEnabled(true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}