package br.com.patrocine.patrocine.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import br.com.patrocine.patrocine.BuildConfig;
import br.com.patrocine.patrocine.R;

public class SplashScreenActivity extends Activity implements Runnable {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private static final long delay = 1500L;
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(this, delay);

        onCreateComponents();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void run() {
        //startActivity(new Intent(this, MainActivity.class));
        startActivity(new Intent(this, MoviesTest.class));
        finish();
    }

    void onCreateComponents(){
        tvVersion = findViewById(R.id.version);
        String text = "version: " + BuildConfig.VERSION_NAME;
        tvVersion.setText(text);
    }
}
