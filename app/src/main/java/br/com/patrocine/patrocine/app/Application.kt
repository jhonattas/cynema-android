package br.com.patrocine.patrocine.app

import android.app.Application

class Application : Application() {

    val TAG = Application::class.java.simpleName

    var mInstance: Application? = null

    override fun onCreate() {
        super.onCreate()
    }

}