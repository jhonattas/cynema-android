package br.com.patrocine.patrocine.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import br.com.patrocine.patrocine.BuildConfig;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Movie;
import br.com.patrocine.patrocine.ui.fragments.MapFragment;
import br.com.patrocine.patrocine.ui.fragments.MovieFragment;
import br.com.patrocine.patrocine.ui.fragments.NavigationDrawerFragment;
import br.com.patrocine.patrocine.ui.fragments.OnlineFragment;
import br.com.patrocine.patrocine.ui.fragments.TicketsFragment;
import br.com.patrocine.patrocine.ui.interfaces.NavigationDrawerCallbacks;
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks, OnFragmentInteractionListener {

    private static final String CLASS_NAME = MainActivity.class.getSimpleName();
    NavigationDrawerFragment mNavigationDrawerFragment;
    CharSequence mTitle;
    String currentOption = "";
    FragmentManager fragmentManager = getSupportFragmentManager();
    ImageView ownAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));

        openMovies();

        onCreateComponents();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        fragmentManager = getSupportFragmentManager();

        onSectionAttached(position+1);
        String[][] navigation = BuildConfig.navigation_map;
        int tamanho = navigation.length;
        Log.d(CLASS_NAME, "tamanho do menu: "+ tamanho);
        Log.d(CLASS_NAME, "elemento: "+ navigation[position][1]);

        setTitle(getResources().getString(R.string.app_name));

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                openMovies();
                break;
            case 2:
                openTickets();
                break;
            case 3:
                openBomboniere();
                break;
            case 4:
                openFaq();
                break;
            case 5:
                openLocation();
                break;
            case 6:
                closeApp();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void onFragmentInteraction(Object obj) {
        Movie movie = (Movie) obj;
        Intent i = new Intent(this, MovieDetailsActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("movie", movie);
        b.putString("type", currentOption);
        i.putExtras(b);
        startActivity(i);
    }

    void onCreateComponents(){
        String url = "https://api.patrocine.com.br/static/data/partners/partner_chaplin.png";

        ownAd = findViewById(R.id.adView);

        Glide.with(this)
                .load(url)
                .into(ownAd);

    }

    @Override
    public void onBackPressed() {
        this.closeApp();
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
}
