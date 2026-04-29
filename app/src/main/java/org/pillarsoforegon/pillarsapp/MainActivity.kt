package org.pillarsoforegon.pillarsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import org.pillarsoforegon.pillarsapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) 

        binding.appBarMain.fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:pillarsoforegon@gmail.com")
            }
            startActivity(intent)
        }

        val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?)!!
        val navController = navHostFragment.navController

        binding.navView?.let {
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_home, R.id.nav_about, R.id.nav_find_child_care, R.id.nav_join_afscme, R.id.nav_benefits
                ),
                binding.drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            it.setupWithNavController(navController)
        }

        binding.appBarMain.contentMain.bottomNavView?.let {
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_home, R.id.nav_about, R.id.nav_find_child_care, R.id.nav_join_afscme, R.id.nav_benefits
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            it.setupWithNavController(navController)
        }

        binding.appBarMain.mainMenuIcon?.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            if (isLoggedIn) {
                popupMenu.menuInflater.inflate(R.menu.profile_menu_logged_in, popupMenu.menu)
            } else {
                popupMenu.menuInflater.inflate(R.menu.profile_menu, popupMenu.menu)
            }

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_login -> {
                        navController.navigate(R.id.nav_login)
                        true
                    }

                    R.id.action_register -> {
                        navController.navigate(R.id.nav_registration)
                        true
                    }

                    R.id.action_settings -> {
                        // Navigate to settings fragment if it exists, or show a message
                        try {
                            navController.navigate(R.id.nav_settings)
                        } catch (e: Exception) {
                            // Handle if nav_settings doesn't exist in graph
                        }
                        true
                    }

                    R.id.action_logout -> {
                        isLoggedIn = false
                        // Handle logout logic
                        true
                    }

                    R.id.action_privacy_policy -> {
                        navController.navigate(R.id.nav_privacy_policy)
                        true
                    }

                    R.id.action_privacy_policy_spanish -> {
                        navController.navigate(R.id.nav_privacy_policy_spanish)
                        true
                    }

                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false // Disable default options menu
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}