package com.example.localization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : MyLocalizationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(getCurrentLanguageString())
        setLanguage("uz")
        println(getCurrentLanguageString())

    }
}