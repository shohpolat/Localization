package com.example.localization

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.*

abstract class MyLocalizationActivity:AppCompatActivity(),OnLocaleChangedListener {

    private val localizationDelegate = LocalizationActivityDelegate(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        localizationDelegate.addOnLocaleChangedListener(this)
        localizationDelegate.onCreate()
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        localizationDelegate.onResume(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun attachBaseContext(newBase: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            applyOverrideConfiguration(localizationDelegate.updateConfigurationLocale(newBase))
            super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
//            super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
        } else {
//            super.attachBaseContext(CalligraphyContextWrapper.wrap(localizationDelegate.attachBaseContext(newBase)))
            super.attachBaseContext(ViewPumpContextWrapper.wrap(localizationDelegate.attachBaseContext(newBase)))
        }
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

    override fun getResources(): Resources {
        return localizationDelegate.getResources(super.getResources())
    }

    override fun onBeforeLocaleChanged() {}

    override fun onAfterLocaleChanged() {}

    fun setLanguage(language: String) {
        var mLang = language
        if (mLang.isEmpty()) mLang = "ru"
        localizationDelegate.setLanguage(this, mLang)
    }

    fun setLanguage(language: String, country: String) {
        localizationDelegate.setLanguage(this, language, country)
    }

    fun setLanguage(locale: Locale) {
        localizationDelegate.setLanguage(this, locale)
    }

    fun getCurrentLanguage(): Locale {
        return localizationDelegate.getLanguage(this)
    }

    fun getCurrentLanguageString(): String {
        return getCurrentLanguage().displayLanguage
    }


}