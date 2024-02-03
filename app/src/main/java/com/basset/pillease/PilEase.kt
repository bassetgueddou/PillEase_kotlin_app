package com.basset.pillease

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class PilEase : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}