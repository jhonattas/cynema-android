package br.com.patrocine.patrocine.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import br.com.patrocine.patrocine.BuildConfig
import br.com.patrocine.patrocine.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : Activity(), Runnable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed(this, delay)

        onCreateComponents()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    override fun run() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        // startActivity(new Intent(this, MoviesTest.class));
        finish()
    }

    internal fun onCreateComponents() {
        val text = "version: " + BuildConfig.VERSION_NAME
        version.text = text
    }

    companion object {

        private val TAG = SplashScreenActivity::class.java.simpleName
        private val delay = 1500L
    }
}