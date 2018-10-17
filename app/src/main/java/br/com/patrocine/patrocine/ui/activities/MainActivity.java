package br.com.patrocine.patrocine.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.io.Config;
import br.com.patrocine.patrocine.model.Movie;
import br.com.patrocine.patrocine.ui.fragments.MapFragment;
import br.com.patrocine.patrocine.ui.fragments.MovieFragment;
import br.com.patrocine.patrocine.ui.fragments.NavigationDrawerFragment;
import br.com.patrocine.patrocine.ui.fragments.OnlineFragment;
import br.com.patrocine.patrocine.ui.interfaces.NavigationDrawerCallbacks;
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationDrawerCallbacks, OnFragmentInteractionListener, Runnable {

    private int mInterval = 3000; // 5 seconds by default, can be changed later

    private static final String CLASS_NAME = MainActivity.class.getSimpleName();
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private AppCompatImageView ownAd;
    private String adUrl;
    private int adCount = 0;
    private Handler mHandler;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });

        onCreateComponents();
        openMovies();

        startRepeatingTask();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    //displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    // Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                    //txtMessage.setText(message);
                }
            }
        };
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case R.id.nav_movies:
                openMovies();
                break;
            case R.id.nav_tickets:
                openTickets();
                setTitle(R.string.title_tickets);
                break;
            case R.id.nav_promos:
                setTitle(R.string.promocoes);
                openPromos();
                break;
            case R.id.nav_bomboniere:
                setTitle(R.string.title_bomboniere);
                openBomboniere();
                break;
            case R.id.nav_faq:
                setTitle(R.string.title_faq);
                openFaq();
                break;
            case R.id.nav_location:
                setTitle(R.string.title_location);
                openLocation();
                break;
            case R.id.nav_close:
                closeApp();
                break;
        }
    }

    void onCreateComponents(){
        mHandler = new Handler();
        ownAd = (AppCompatImageView) findViewById(R.id.adView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        onSectionAttached(item.getItemId());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            this.closeApp();
        }
    }

    void closeApp(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        MainActivity.this.finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.confirm_exit).setPositiveButton(android.R.string.yes, dialogClickListener)
                .setNegativeButton(android.R.string.no, dialogClickListener).show();
    }

    void openMovies(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, MovieFragment.newInstance())
                .commit();
    }

    void openTickets(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, OnlineFragment.newInstance("tickets"))
                .commit();
    }

    void openPromos(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, OnlineFragment.newInstance("promocoes"))
                .commit();
    }

    void openBomboniere(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, OnlineFragment.newInstance("bomboniere"))
                .commit();
    }

    void openFaq(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, OnlineFragment.newInstance("faq"))
                .commit();
    }

    void openLocation(){
        setTitle(R.string.map);
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, MapFragment.newInstance())
                .commit();
    }

    @Override
    public void run() {
        try {
            switch (adCount){
                case 0 :
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_chaplin.png";
                    loadImage();
                    this.adCount = adCount + 1;
                    Log.e(CLASS_NAME, "id 0");
                    break;
                case 1:
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_batata_minas.png";
                    loadImage();
                    this.adCount = adCount + 1;
                    Log.e(CLASS_NAME, "id 1");
                    break;
                case 2:
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_cofee_shop.png";
                    loadImage();
                    this.adCount = adCount + 1;
                    Log.e(CLASS_NAME, "id 2");
                    break;
                case 3:
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_modulo.png";
                    loadImage();
                    this.adCount = adCount + 1;
                    Log.e(CLASS_NAME, "id 3");
                    break;
                case 4:
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_toys.png";
                    loadImage();
                    this.adCount = 0;
                    Log.e(CLASS_NAME, "id 4");
                    break;
                default:
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_chaplin.png";
                    loadImage();
                    break;
            }
        } finally {
            // 100% guarantee that this always happens, even if
            // your update method throws an exception
            mHandler.postDelayed(this, mInterval);
        }

    }

    void loadImage(){
        Picasso.get()
                .load(this.adUrl)
                .resize(320, 50)
                .into(ownAd);
    }

    void startRepeatingTask() {
        this.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(this);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    @Override
    public void onFragmentInteraction(Object obj) {
        Movie movie = (Movie) obj;
        Intent i = new Intent(this, MovieDetailsActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("movie", movie);
        i.putExtras(b);
        startActivity(i);
    }

    public void setupNavigation() {
        
    }
}