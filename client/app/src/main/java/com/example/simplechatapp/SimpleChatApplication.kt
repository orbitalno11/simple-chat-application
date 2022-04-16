package com.example.simplechatapp

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class SimpleChatApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}