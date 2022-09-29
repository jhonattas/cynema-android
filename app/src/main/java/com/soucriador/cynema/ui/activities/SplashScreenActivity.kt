package com.soucriador.cynema.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.soucriador.cynema.BuildConfig
import com.soucriador.cynema.R
import com.soucriador.cynema.model.Layout
import com.soucriador.cynema.rest.ApiClient
import com.soucriador.cynema.ui.interfaces.ApiInterface
import kotlinx.android.synthetic.main.activity_splash_screen.*
import retrofit2.Call
import retrofit2.Response

class SplashScreenActivity : Activity(), Runnable {

    private lateinit var apiService: ApiInterface
    private var layout: Layout? = null
    var i: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        fetchConfig()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    override fun run() {
        val b = Bundle()
        b.putSerializable("layout", layout)
        i?.putExtras(b)
        startActivity(i)
        finish()
    }

    internal fun onCreateComponents() {
        val text = "version: " + BuildConfig.VERSION_NAME
        version.text = text
        Handler().postDelayed(this, delay)
    }

    companion object {

        private val TAG = SplashScreenActivity::class.java.simpleName
        private val delay = 1500L
    }

    fun fetchConfig() {
        apiService = com.soucriador.cynema.rest.ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.config()
        call.enqueue(object : retrofit2.Callback<Layout> {
            override fun onResponse(call: Call<Layout>, response: Response<Layout>) {
                if (response.body() != null) {
                    layout = response.body()
                    i = Intent(baseContext, WebDestaqueActivity::class.java)
                    layout = response.body()
                    onCreateComponents()
                }
            }

            override fun onFailure(call: Call<Layout>, t: Throwable) {
                Toast.makeText(applicationContext, R.string.failed_connection, Toast.LENGTH_SHORT).show()
            }
        })
    }
}