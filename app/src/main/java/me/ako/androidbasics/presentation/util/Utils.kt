package me.ako.androidbasics.presentation.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.navigation.NavController
import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.ActivityType
import me.ako.androidbasics.data.model.PathwayEntity
import me.ako.androidbasics.data.model.UnitEntity
import me.ako.androidbasics.presentation.view.FragmentActivitiesDirections
import me.ako.androidbasics.presentation.view.FragmentBookmarksDirections
import me.ako.androidbasics.presentation.view.FragmentPathwaysDirections
import me.ako.androidbasics.presentation.view.FragmentUnitsDirections

class Utils {
    enum class NavFragment(val id: String) {
        UnitsFragment("me.ako.androidbasics:id/fragmentUnits"),
        PathwaysFragment("me.ako.androidbasics:id/fragmentPathways"),
        ActivitiesFragment("me.ako.androidbasics:id/fragmentActivities"),
        BookmarksFragment("me.ako.androidbasics:id/fragmentBookmarks")
    }
    
    fun handleSearchUnitClick(navController: NavController, unit: UnitEntity) {
        when (navController.currentDestination?.displayName) {
            NavFragment.UnitsFragment.id -> {
                val action =
                    FragmentUnitsDirections.actionFragmentUnitsToFragmentPathways(unit.id)
                navController.navigate(action)
            }
            NavFragment.PathwaysFragment.id -> {
                val action =
                    FragmentPathwaysDirections.actionFragmentPathwaysSelf(unit.id)
                navController.navigate(action)
            }
            NavFragment.ActivitiesFragment.id -> {
                val action =
                    FragmentActivitiesDirections.actionFragmentActivitiesToFragmentPathways(
                        unit.id
                    )
                navController.navigate(action)
            }
            NavFragment.BookmarksFragment.id -> {
                val action =
                    FragmentBookmarksDirections.actionFragmentBookmarksToFragmentPathways(
                        unit.id
                    )
                navController.navigate(action)
            }
        }
    }

    fun handleSearchPathwayClick(navController: NavController, pathway: PathwayEntity) {
        when (navController.currentDestination?.displayName) {
            NavFragment.UnitsFragment.id -> {
                val action =
                    FragmentUnitsDirections.actionFragmentUnitsToFragmentActivities(
                        pathway.id,
                        pathway.number
                    )
                navController.navigate(action)
            }
            NavFragment.PathwaysFragment.id -> {
                val action =
                    FragmentPathwaysDirections.actionFragmentPathwaysToFragmentActivities(
                        pathway.id,
                        pathway.number
                    )
                navController.navigate(action)
            }
            NavFragment.ActivitiesFragment.id -> {
                val action =
                    FragmentActivitiesDirections.actionFragmentActivitiesSelf(
                        pathway.id,
                        pathway.number
                    )
                navController.navigate(action)
            }
            NavFragment.BookmarksFragment.id -> {
                val action =
                    FragmentBookmarksDirections.actionFragmentBookmarksToFragmentActivities(
                        pathway.id,
                        pathway.number
                    )
                navController.navigate(action)
            }
        }
    }

    fun handleActivityClick(activity: Activity, it: ActivityEntity) {
        if(it.type == ActivityType.Video) {
            youtube(activity, it)
        }
        else {
            url(activity, it.url)
        }
    }

    private fun youtube(activity: Activity, it: ActivityEntity) {
        // Try to generate a direct intent to the YouTube app
        var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
        if (intent.resolveActivity(activity.packageManager) == null) {
            // YouTube app isn't found, use the web url
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
        }
        activity.startActivity(intent)
    }

    fun endpoint(activity: Activity, endpoint: String) {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/$endpoint"))
        activity.startActivity(intent)
    }

    fun url(activity: Activity, url: String) {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(intent)
    }
}