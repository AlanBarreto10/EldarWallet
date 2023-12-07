package com.example.eldarwallet

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class EldarWallet: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}