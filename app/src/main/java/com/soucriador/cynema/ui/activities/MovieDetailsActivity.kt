package br.com.soucriador.cynema.cynema.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import br.com.patrocine.cynema.R
import br.com.patrocine.cynema.model.Movie
import br.com.soucriador.cynema.cynema.rest.ApiClient
import br.com.soucriador.cynema.cynema.ui.adapters.SessionAdapter
import br.com.patrocine.cynema.ui.fragments.tabexamples.*
import br.com.patrocine.cynema.ui.interfaces.ApiInterface
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

class MovieDetailsActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    private lateinit var apiService: ApiInterface

    private var movie: Movie? = null
    private var tvMovieSinopse: TextView? = null
    private var buyOnline: Button? = null
    private var movieSlider: ImageView? = null
    private var sessions: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details_tabbed)

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById<View>(R.id.viewpager) as ViewPager?
        val b = intent.extras
        movie = b!!.getSerializable("movie") as Movie
        title = movie!!.fullTitle
        fetchMovieDetails()

        tabLayout = findViewById<View>(R.id.tabs) as TabLayout?
        tabLayout!!.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        var i = 0
        var diaHeader = ""
        while(i < movie?.grid!!.size) {
            var dia = movie?.grid!![i].day!!
            if(!dia.equals(diaHeader)) {
                adapter.addFrag(FragmentOne(), dia)
                diaHeader = dia
            }
            i++
        }

        viewPager.adapter = adapter
        onCreateComponents()
    }

    fun openShop() {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://ingressoplus.com.br/patrocine"))
        startActivity(i)
    }

    fun watch_video(url: String?) {
        val yt_play = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        val chooser = Intent.createChooser(yt_play, "Open With")

        if (yt_play.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        }
    }

    fun fetchMovieDetails() {
        apiService = br.com.soucriador.cynema.cynema.rest.ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.oneMovie(movie?.title!!)
        call.enqueue(object : retrofit2.Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.body() != null) {
                    movie = response.body()
                    setupViewPager(viewPager!!)
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Toast.makeText(applicationContext, R.string.failed_connection, Toast.LENGTH_SHORT).show()
                //Log.e(CLASS_NAME, t.localizedMessage)
            }
        })
    }

    fun onCreateComponents() {
        tvMovieSinopse = findViewById(R.id.movie_sinopse)
        movieSlider = findViewById(R.id.movieSlider)
        sessions = findViewById(R.id.rcSessions)
        buyOnline = findViewById<Button>(R.id.buyOnline)

        tvMovieSinopse?.text = movie?.sinopse
        Log.e("AQUI ", movie.toString())
        Picasso.get().load(movie?.image_mini).into(movieSlider)

        val sessionAdapter = br.com.soucriador.cynema.cynema.ui.adapters.SessionAdapter(movie?.grid)
        val layoutManager = LinearLayoutManager(this)
        sessions?.layoutManager = layoutManager
        sessions?.adapter = sessionAdapter

        buyOnline!!.setOnClickListener{
            openShop()
        }

    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}