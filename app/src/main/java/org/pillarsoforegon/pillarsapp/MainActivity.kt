package org.pillarsoforegon.pillarsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.pillarsoforegon.pillarsapp.databinding.ActivityMainBinding
import org.pillarsoforegon.pillarsapp.ui.registration.User

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        setupNavigation()
        setupUserObserver()

        binding.appBarMain.fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:pillarsoforegon@gmail.com")
            }
            startActivity(intent)
        }

        binding.appBarMain.mainMenuIcon?.setOnClickListener {
            showPopupMenu(it)
        }
    }

    private fun setupNavigation() {
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
    }

    private fun setupUserObserver() {
        auth.addAuthStateListener { firebaseAuth ->
            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                database.getReference("users").child(currentUser.uid)
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val user = snapshot.getValue(User::class.java)
                            if (user != null && user.firstName.isNotEmpty()) {
                                binding.appBarMain.welcomeText.text = getString(R.string.welcome_user, user.firstName)
                            } else {
                                binding.appBarMain.welcomeText.text = getString(R.string.welcome_provider)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            binding.appBarMain.welcomeText.text = getString(R.string.welcome_provider)
                        }
                    })
            } else {
                binding.appBarMain.welcomeText.text = getString(R.string.welcome_provider)
            }
        }
    }

    private fun showPopupMenu(view: android.view.View) {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val popupMenu = PopupMenu(this, view)
        val currentUser = auth.currentUser
        
        if (currentUser != null) {
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
                    try {
                        navController.navigate(R.id.nav_settings)
                    } catch (e: Exception) {}
                    true
                }
                R.id.action_logout -> {
                    auth.signOut()
                    true
                }
                R.id.action_privacy_policy -> {
                    navController.navigate(R.id.nav_privacy_policy)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
