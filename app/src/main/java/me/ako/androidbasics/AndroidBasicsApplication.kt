package me.ako.androidbasics

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidBasicsApplication: Application() {
    /*val database: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }*/
}