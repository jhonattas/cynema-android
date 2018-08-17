package br.com.patrocine.patrocine.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Movie;
import br.com.patrocine.patrocine.ui.adapters.SessionAdapter;

public class MovieDetailsActivity extends AppCompatActivity {

    private Movie movie;
    private TextView tvMovieSinopse;
    private Toolbar toolbar;
    private ImageView movieSlider;
    private RecyclerView sessions;

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
        movieSlider = findViewById(R.id.movieSlider);
        tvMovieSinopse = findViewById(R.id.movie_sinopse);
        tvMovieSinopse.setText(movie.getSinopse());
        sessions = findViewById(R.id.rcSessions);

        setTitle(movie.getFullTitle());

        // monta a imagem de destaque do filme no topo da tela
        Glide.with(this)
                .load(movie.getImage_mini())
                .into(movieSlider);

        SessionAdapter sessionAdapter = new SessionAdapter(movie.getGrid());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        sessions.setLayoutManager(layoutManager);
        sessions.setAdapter(sessionAdapter);

    }

    void watch_video(String url) {
        Intent yt_play = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Intent chooser = Intent.createChooser(yt_play , "Open With");

        if (yt_play .resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }
}
