package com.oss.demo

import android.app.Application
import com.oss.demo.database.ObjectBox

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}