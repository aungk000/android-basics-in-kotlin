package me.ako.androidbasics.domain.util

import android.content.SearchRecentSuggestionsProvider

class SearchProvider : SearchRecentSuggestionsProvider() {
    companion object {
        const val AUTHORITY = "me.ako.androidbasics.SearchProvider"
        const val MODE = DATABASE_MODE_QUERIES // or DATABASE_MODE_2LINES
    }

    init {
        setupSuggestions(AUTHORITY, MODE)
    }
}