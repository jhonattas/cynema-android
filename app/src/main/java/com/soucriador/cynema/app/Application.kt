package br.com.soucriador.cynema.cynema.app

import android.app.Application

class Application : Application() {

    val TAG = Application::class.java.simpleName

    var mInstance: Application? = null

    override fun onCreate() {
        super.onCreate()
    }

}