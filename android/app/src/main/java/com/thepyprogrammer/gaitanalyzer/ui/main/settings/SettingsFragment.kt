package com.thepyprogrammer.gaitanalyzer.ui.main.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import androidx.transition.TransitionInflater
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.ui.main.MainActivity

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val themePreference: SwitchPreferenceCompat? = findPreference("theme")
        themePreference?.setOnPreferenceChangeListener { _: Preference, any: Any ->
            AppCompatDelegate.setDefaultNightMode(if (any as Boolean) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
            true
        }

        val logout: Preference? = findPreference("logout")
        logout?.onPreferenceClickListener = Preference.OnPreferenceClickListener { //code for what you want it to do
            (activity as MainActivity).logout()
            true
        }

    }
}