package me.ako.androidbasics

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView
import com.google.android.material.search.SearchView.TransitionState
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import me.ako.androidbasics.databinding.ActivityMainBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.presentation.presenter.SearchAdapter
import me.ako.androidbasics.presentation.util.Utils
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AppViewModel by viewModels()
    @Inject
    lateinit var utils: Utils

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
        appBarLayout.statusBarForeground = MaterialShapeDrawable.createWithElevationOverlay(this)
    }

    enum class NavFragment(val id: Int) {
        UnitsFragment(2131296472),
        PathwaysFragment(2131296471),
        ActivitiesFragment(2131296469),
        BookmarksFragment(2131296470)
    }

    private fun setupSearch(
        searchBar: SearchBar, searchView: SearchView
    ) {
        searchView.apply {
            //setupWithSearchBar(searchBar)

            val adapter = SearchAdapter(
                {
                    utils.handleSearchUnitClick(navController, it)
                    hide()
                },
                {
                    utils.handleSearchPathwayClick(navController, it)
                    hide()
                },
                {
                    utils.handleActivityClick(this@MainActivity, it)
                }
            )
            binding.recyclerViewSearch.adapter = adapter

            viewModel.searchData.observe(this@MainActivity) { list ->
                adapter.submitList(list)
                if(binding.progressSearch.isShown) {
                    binding.progressSearch.hide()
                }
                Log.d("MainActivity", "search: ended")
            }

            editText.addTextChangedListener {
            }

            editText.setOnEditorActionListener { view, actionId, event ->
                if (editText.text.isNotEmpty()) {
                    Log.d("MainActivity", "search: started")
                    binding.progressSearch.show()
                    val query = editText.text.toString().lowercase()
                    viewModel.searchDb(query)
                }

                this.clearFocusAndHideKeyboard()
                true
            }

            addTransitionListener { view, previousState, newState ->
                if (newState == TransitionState.HIDING) {
                    adapter.submitList(null)
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
            utils.endpoint(this, "")
        }

        navigationView.setNavigationItemSelectedListener {
            //it.isChecked = true
            drawerLayout.close()

            when (it.itemId) {
                R.id.menu_platform -> {
                    utils.endpoint(this, "about")
                }
                R.id.menu_android_studio -> {
                    utils.endpoint(this, "studio")
                }
                R.id.menu_google_play -> {
                    utils.endpoint(this, "distribute")
                }
                R.id.menu_jetpack -> {
                    utils.endpoint(this, "jetpack")
                }
                R.id.menu_kotlin -> {
                    utils.endpoint(this, "kotlin")
                }
                R.id.menu_docs -> {
                    utils.endpoint(this, "docs")
                }
                R.id.menu_games -> {
                    utils.endpoint(this, "games")
                }
            }

            true
        }
    }
}