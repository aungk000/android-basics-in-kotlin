package me.ako.androidbasics

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.MaterialShapeDrawable
import me.ako.androidbasics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            setupToolbarWithDrawer(toolbar, drawerLayout)
            setupDrawerNavigation(drawerLayout, navigationView)
        }

        binding.appbarLayout.statusBarForeground =
            MaterialShapeDrawable.createWithElevationOverlay(this)
    }

    private fun setupToolbarWithDrawer(toolbar: MaterialToolbar, drawerLayout: DrawerLayout) {
        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        toolbar.inflateMenu(R.menu.menu_main)
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
        }
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