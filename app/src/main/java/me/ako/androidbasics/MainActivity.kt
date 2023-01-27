package me.ako.androidbasics

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.MaterialShapeDrawable
import me.ako.androidbasics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            setupToolbarWithDrawer(toolbar, drawerLayout)
            setupDrawerNavigation(drawerLayout, navigationView)
        }

        binding.appbarLayout.setStatusBarForegroundColor(
            ContextCompat.getColor(this, R.color.grey_dark)
        )
        /*binding.appbarLayout.statusBarForeground =
            MaterialShapeDrawable.createWithElevationOverlay(this)*/
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupToolbarWithDrawer(toolbar: MaterialToolbar, drawerLayout: DrawerLayout) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        //val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(toolbar, navController, drawerLayout)

        /*toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }*/

        /*toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_bookmark -> {
                    true
                }
                R.id.menu_settings -> {
                    true
                }
                R.id.menu_about -> {
                    true
                }
                else -> false
            }
        }*/
    }

    private fun setupDrawerNavigation(drawerLayout: DrawerLayout, navigationView: NavigationView) {
        val imgHeader: ImageView = navigationView.getHeaderView(0).findViewById(R.id.img_developers)
        imgHeader.setOnClickListener {
            drawerLayout.close()
            intentActionView("")
        }

        navigationView.setNavigationItemSelectedListener {
            it.isChecked = true
            drawerLayout.close()

            when (it.itemId) {
                R.id.menu_platform -> {
                    intentActionView("about")
                }
                R.id.menu_android_studio -> {
                    intentActionView("studio")
                }
                R.id.menu_google_play -> {
                    intentActionView("distribute")
                }
                R.id.menu_jetpack -> {
                    intentActionView("jetpack")
                }
                R.id.menu_kotlin -> {
                    intentActionView("kotlin")
                }
                R.id.menu_docs -> {
                    intentActionView("docs")
                }
                R.id.menu_games -> {
                    intentActionView("games")
                }
            }

            true
        }
    }

    private fun intentActionView(endPoint: String) {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/$endPoint"))
        startActivity(intent)
    }
}