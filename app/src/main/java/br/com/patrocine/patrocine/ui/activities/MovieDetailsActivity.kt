package br.com.patrocine.patrocine.ui.activities

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.patrocine.patrocine.R
import br.com.patrocine.patrocine.model.Movie
import br.com.patrocine.patrocine.rest.ApiClient
import br.com.patrocine.patrocine.ui.adapters.SessionAdapter
import br.com.patrocine.patrocine.ui.interfaces.ApiInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import retrofit2.Call
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var apiService: ApiInterface

    private var movie: Movie? = null
    private var tvMovieSinopse: TextView? = null
    private var toolbar: Toolbar? = null
    private var buyOnline: Button? = null
    private var movieSlider: ImageView? = null
    private var sessions: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        fab.setOnClickListener(View.OnClickListener { watch_video(movie!!.trailer) })
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val b = intent.extras
        movie = b!!.getSerializable("movie") as Movie
        title = movie!!.fullTitle
        fetchMovieDetails()
    }

    fun onCreateComponents() {
        tvMovieSinopse = findViewById(R.id.movie_sinopse)
        movieSlider = findViewById(R.id.movieSlider)
        sessions = findViewById(R.id.rcSessions)
        buyOnline = findViewById<Button>(R.id.buyOnline)

        tvMovieSinopse?.text = movie?.sinopse
        Log.e("AQUI ", movie.toString())
        Picasso.with(this).load(movie?.image_mini).into(movieSlider)

        val sessionAdapter = SessionAdapter(movie?.grid)
        val layoutManager = LinearLayoutManager(this)
        sessions?.layoutManager = layoutManager
        sessions?.adapter = sessionAdapter

        buyOnline!!.setOnClickListener{
            openShop()
        }

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
        apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.oneMovie(movie?.title!!)
        call.enqueue(object : retrofit2.Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.body() != null) {
                    movie = response.body()
                    onCreateComponents()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Toast.makeText(applicationContext, R.string.failed_connection, Toast.LENGTH_SHORT).show()
                //Log.e(CLASS_NAME, t.localizedMessage)
            }
        })
    }
}