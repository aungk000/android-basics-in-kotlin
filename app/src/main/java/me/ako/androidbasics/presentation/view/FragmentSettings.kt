package me.ako.androidbasics.presentation.view

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceFragmentCompat
import dagger.hilt.android.AndroidEntryPoint
import me.ako.androidbasics.R
import me.ako.androidbasics.presentation.util.Utils
import javax.inject.Inject

@AndroidEntryPoint
class FragmentSettings : PreferenceFragmentCompat(), OnPreferenceChangeListener {
    @Inject lateinit var utils: Utils

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        findPreference<ListPreference>("theme")?.onPreferenceChangeListener = this
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
        return when(preference.key) {
            "theme" -> {
                utils.setTheme(newValue as String)
                true
            }
            else -> false
        }
    }
}