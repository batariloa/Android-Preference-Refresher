package com.example.dz04

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


class MainActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ime = findViewById<TextView>(R.id.tv_ime)

        val adresa = findViewById<TextView>(R.id.tv_adresa)


        var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)



        ime.text = sharedPreferences.getString("ime","Default name")
        adresa.text = sharedPreferences.getString("adresa", "Default adress")




    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: Preference?
    ): Boolean {


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_settings,PrefUniversityImpl())
            .addToBackStack(null)
            .commit()
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                setTitle("Yes")
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return true
    }




}