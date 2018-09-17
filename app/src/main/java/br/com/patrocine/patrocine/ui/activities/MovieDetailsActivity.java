package br.com.patrocine.patrocine.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
        String prefix = "https://api.patrocine.com.br/v1/static/images/mobile/";

        Picasso.get().load(prefix + movie.getImage_mini()).into(movieSlider);

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
