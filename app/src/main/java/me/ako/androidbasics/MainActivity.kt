package me.ako.androidbasics

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.util.Log
import android.util.TypedValue
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.inputmethod.EditorInfoCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.room.util.query
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView
import com.google.android.material.search.SearchView.TransitionState
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.ako.androidbasics.databinding.ActivityMainBinding
import me.ako.androidbasics.domain.util.SearchProvider

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        binding.apply {
            setupAppbar(appbarLayout)
            setupSearch(searchBar, searchView)
            setupToolbar(toolbar, drawerLayout)
            setupNavigationDrawer(drawerLayout, navigationView)
        }

        handleBackPressed(navHost)
    }

    private fun handleBackPressed(navHost: NavHostFragment) {
        onBackPressedDispatcher.addCallback(this) {
            if (binding.drawerLayout.isOpen) {
                binding.drawerLayout.close()
            } else if (binding.searchView.isShowing) {
                binding.searchView.hide()
            } else {
                val backStack = navHost.childFragmentManager.backStackEntryCount
                if (backStack == 0) {
                    finish()
                } else {
                    navController.navigateUp()
                }
            }
        }
    }

    // deprecated but works
    /*override fun onBackPressed() {
        if (binding.searchView.isShowing || binding.searchView.isShowing) {
            binding.searchView.hide()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }*/

    private fun setupAppbar(appBarLayout: AppBarLayout) {
        val typedValue = TypedValue()
        theme.resolveAttribute(android.R.attr.colorBackground, typedValue, true)
        appBarLayout.setBackgroundColor(typedValue.data)

        /*appBarLayout.setStatusBarForegroundColor(
            ContextCompat.getColor(this, R.color.grey_dark)
        )*/
        appBarLayout.statusBarForeground =
            MaterialShapeDrawable.createWithElevationOverlay(this)
    }

    private fun setupSearch(
        searchBar: SearchBar,
        searchView: SearchView
    ) {
        searchView.apply {
            setupWithSearchBar(searchBar)
            editText.setOnEditorActionListener { view, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (editText.text.isNotEmpty()) {
                        Snackbar.make(this, editText.text.toString(), Snackbar.LENGTH_SHORT)
                            .show()
                    }

                    view.clearFocus()
                    true
                } else {
                    false
                }
            }

            addTransitionListener { view, previousState, newState ->
                if (newState == TransitionState.SHOWING) {
                    // Handle search view opened.
                }
            }
        }
    }

    private fun setupToolbar(toolbar: MaterialToolbar, drawerLayout: DrawerLayout) {
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

    override fun onSearchRequested(): Boolean {
        val appData = Bundle().apply {
            putBoolean("JARGON", true)
        }
        startSearch(null, false, appData, false)
        return true
    }

    private fun setupNavigationDrawer(drawerLayout: DrawerLayout, navigationView: NavigationView) {
        val header = navigationView.getHeaderView(0)
        val imgHeader: ImageView = header.findViewById(R.id.img_developers)
        imgHeader.setOnClickListener {
            drawerLayout.close()
            intentActionView("")
        }

        navigationView.setNavigationItemSelectedListener {
            //it.isChecked = true
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