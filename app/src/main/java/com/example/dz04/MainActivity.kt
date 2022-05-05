package com.example.dz04

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

   private lateinit var listener:SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adresa = findViewById<TextView>(R.id.tv_adresa)
        val ime = findViewById<TextView>(R.id.tv_ime)
        val odgovor = findViewById<TextView>(R.id.tv_odgovor)

        listener = SharedPreferences.OnSharedPreferenceChangeListener(fun(
            prefs: SharedPreferences,
            key: String
        ) {
            println("Aktiviran listener")

            when(key){
                 "ime"->{
                    ime.text = "Ime univerziteta:"+ prefs.getString("ime", "Default ime")
                 }
                "adresa" ->{
                    adresa.text ="Adresa univerziteta:"+ prefs.getString("adresa","Default adresa")
                }

                "odgovor" ->{
                    val odgovorOdabran = prefs.getString("odgovor", "1")
                    var tacno = false
                    if(odgovorOdabran== "2")
                         tacno = true

                    odgovor.text="Odgovor je: $tacno"
                }
            }

        })


    }


    override fun onResume() {
        super.onResume()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
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
                setTitle("Root preferences")
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return true
    }




}