package br.com.patrocine.patrocine.ui.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    private Movie movie;
    private TextView tvMovieSinopse;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                watch_video(movie.getTrailer());
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle b = getIntent().getExtras();
        movie = (Movie) b.getSerializable("movie");

        onCreateComponents();
    }

    void onCreateComponents(){
        appBarLayout = findViewById(R.id.app_bar);
        tvMovieSinopse = findViewById(R.id.movie_sinopse);
        tvMovieSinopse.setText(movie.getSinopse());

        setTitle(movie.getFullTitle());

        Glide.with(this).load(movie.getSlide01()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    appBarLayout.setBackground(resource);
                }
            }
        });

    }

    void watch_video(String url)
    {
        Intent yt_play = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Intent chooser = Intent.createChooser(yt_play , "Open With");

        if (yt_play .resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }
}
