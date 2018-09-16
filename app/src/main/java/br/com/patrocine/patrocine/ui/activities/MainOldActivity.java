package br.com.patrocine.patrocine.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import br.com.patrocine.patrocine.BuildConfig;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Movie;
import br.com.patrocine.patrocine.ui.fragments.MapFragment;
import br.com.patrocine.patrocine.ui.fragments.MovieFragment;
import br.com.patrocine.patrocine.ui.fragments.NavigationDrawerFragment;
import br.com.patrocine.patrocine.ui.fragments.OnlineFragment;
import br.com.patrocine.patrocine.ui.interfaces.NavigationDrawerCallbacks;
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener;

public class MainOldActivity extends AppCompatActivity implements NavigationDrawerCallbacks, OnFragmentInteractionListener {

    private int mInterval = 1000; // 5 seconds by default, can be changed later

    private static final String CLASS_NAME = MainOldActivity.class.getSimpleName();
    NavigationDrawerFragment mNavigationDrawerFragment;
    CharSequence mTitle;
    String currentOption = "";
    FragmentManager fragmentManager = getSupportFragmentManager();
    ImageView ownAd;
    String adUrl;
    private int adCount = 0;
    private Handler mHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_old);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));

        openMovies();
        onCreateComponents();

        startRepeatingTask();
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
        ownAd = findViewById(R.id.adView);
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
                        MainOldActivity.this.finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainOldActivity.this);
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

    /*
    @Override
    public void run() {

        switch (adCount){
            case 0 :
                this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_modulo.png";
                loadImage();
                adCount = adCount++;
                Log.e(CLASS_NAME, "id 0");
                break;
            case 1:
                this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_batata_minas.png";
                loadImage();
                adCount = adCount++;
                Log.e(CLASS_NAME, "id 1");
                break;
            case 2:
                this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_cofee_shop.png";
                loadImage();
                adCount = adCount++;
                Log.e(CLASS_NAME, "id 2");
                break;
            case 3:
                this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_chaplin.png";
                loadImage();
                adCount = adCount++;
                Log.e(CLASS_NAME, "id 3");
                break;
            case 4:
                this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_toys.png";
                loadImage();
                adCount = adCount++;
                Log.e(CLASS_NAME, "id 4");
                break;
            default:
                this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_chaplin.png";
                loadImage();
                break;
        }
    }
    */

    void loadImage(){
        this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_modulo.png";

        Picasso.get().load(adUrl).into(ownAd);
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                loadImage(); //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }


}
