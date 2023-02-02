package me.ako.androidbasics

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import androidx.appcompat.app.AppCompatActivity
import me.ako.androidbasics.domain.util.SearchProvider

class SearchableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (intent.action == Intent.ACTION_SEARCH) {
            val searchSuggestions =
                SearchRecentSuggestions(this, SearchProvider.AUTHORITY, SearchProvider.MODE)
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                searchSuggestions.saveRecentQuery(query, null)
            }
            //searchSuggestions.clearHistory()

            val jargon: Boolean =
                intent.getBundleExtra(SearchManager.APP_DATA)?.getBoolean("JARGON") ?: false
        }
    }
}