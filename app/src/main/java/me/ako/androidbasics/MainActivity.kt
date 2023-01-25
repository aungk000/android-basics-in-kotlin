package me.ako.androidbasics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.shape.MaterialShapeDrawable
import me.ako.androidbasics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)

        binding.apply {
            //navigationView.setupWithNavController(navController)
            NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)

            navigationView.setNavigationItemSelectedListener {
                it.isChecked = true
                drawerLayout.close()
                true
            }
        }

        binding.appbarLayout.statusBarForeground =
            MaterialShapeDrawable.createWithElevationOverlay(this)
    }
}