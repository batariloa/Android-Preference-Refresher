package com.example.dz04

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class PrefUniversityImpl : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.prefs2)

    }

}