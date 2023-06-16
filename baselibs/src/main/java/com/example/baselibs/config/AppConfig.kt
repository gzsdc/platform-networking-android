package com.example.baselibs.config

import android.app.Application

object AppConfig {

    var debug = false

    private var application: Application? = null

    fun init(application: Application) {
        this.application = application
    }

    fun getApplication(): Application {
        if (application == null) {
            throw RuntimeException("Please init in Application#onCreate first.")
        }
        return application!!
    }

    fun openDebug() {
        debug = true
    }

}