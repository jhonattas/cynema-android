package br.com.patrocine.patrocine.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.patrocine.patrocine.R
import br.com.patrocine.patrocine.model.Movie
import br.com.patrocine.patrocine.ui.adapters.SessionAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private var movie: Movie? = null
    private var tvMovieSinopse: TextView? = null
    private var toolbar: Toolbar? = null
    private var movieSlider: ImageView? = null
    private var sessions: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        fab.setOnClickListener(View.OnClickListener { watch_video(movie!!.trailer) })
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val b = intent.extras
        movie = b!!.getSerializable("movie") as Movie
        onCreateComponents()
    }

    internal fun onCreateComponents() {
        tvMovieSinopse!!.text = movie!!.sinopse
        title = movie!!.fullTitle
        val prefix = "https://api.patrocine.com.br/v1/static/images/mobile/"

        Picasso.get().load(prefix + movie!!.image_mini!!).into(movieSlider)

        val sessionAdapter = SessionAdapter(movie!!.grid)
        val layoutManager = LinearLayoutManager(this)
        sessions!!.layoutManager = layoutManager
        sessions!!.adapter = sessionAdapter

    }

    internal fun watch_video(url: String?) {
        val yt_play = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        val chooser = Intent.createChooser(yt_play, "Open With")

        if (yt_play.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        }
    }
}