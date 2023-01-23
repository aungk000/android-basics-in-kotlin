package me.ako.androidbasics

import android.app.Application
import me.ako.androidbasics.data.AppDatabase

class AndroidBasicsApplication: Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }
}