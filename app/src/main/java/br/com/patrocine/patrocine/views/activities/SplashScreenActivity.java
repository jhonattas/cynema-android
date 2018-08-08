package br.com.patrocine.patrocine.views.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import br.com.patrocine.patrocine.R;
import java.lang.Runnable;

public class SplashScreenActivity extends AppCompatActivity implements Runnable {

    // cria uma espera de de 4.5 segundos
    public long delay = 4500L;

    @Override
    public void run() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(this, delay);
    }


}
