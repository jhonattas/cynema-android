package br.com.patrocine.patrocine.ui.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.GravityCompat
import br.com.patrocine.patrocine.R
import br.com.patrocine.patrocine.io.Config
import br.com.patrocine.patrocine.model.Movie
import br.com.patrocine.patrocine.ui.fragments.MapFragment
import br.com.patrocine.patrocine.ui.fragments.MovieFragment
import br.com.patrocine.patrocine.ui.fragments.NavigationDrawerFragment
import br.com.patrocine.patrocine.ui.fragments.OnlineFragment
import br.com.patrocine.patrocine.ui.interfaces.NavigationDrawerCallbacks
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, NavigationDrawerCallbacks, OnFragmentInteractionListener, Runnable {

    private val mInterval = 3000 // 5 seconds by default, can be changed later
    private val mNavigationDrawerFragment: NavigationDrawerFragment? = null
    private val mTitle: CharSequence? = null
    private var fragmentManager = supportFragmentManager
    private var ownAd: AppCompatImageView? = null
    private var adUrl: String? = null
    private var adCount = 0
    private var mHandler: Handler? = null
    private var mRegistrationBroadcastReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        // navigationView.setNavigationItemSelectedListener(this)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener(View.OnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        })

        onCreateComponents()
        openMovies()

        startRepeatingTask()

        mRegistrationBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {

                // checking for type intent filter
                if (intent.action == Config.REGISTRATION_COMPLETE) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL)

                    //displayFirebaseRegId();

                } else if (intent.action == Config.PUSH_NOTIFICATION) {
                    // new push notification is received

                    val message = intent.getStringExtra("message")

                    // Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                    //txtMessage.setText(message);
                }
            }
        }
    }

    fun onSectionAttached(number: Int) {
        when (number) {
            R.id.navMovies -> {
                openMovies()
            }
            R.id.navTickets -> {
                openTickets()
                setTitle(R.string.title_tickets)
            }
            R.id.navPromos -> {
                setTitle(R.string.promocoes)
                openPromos()
            }
            R.id.navBomboniere -> {
                setTitle(R.string.title_bomboniere)
                openBomboniere()
            }
            R.id.navFaq -> {
                setTitle(R.string.title_faq)
                openFaq()
            }
            R.id.navLocation -> {
                setTitle(R.string.title_location)
                openLocation()
            }
            R.id.navClose -> closeApp()
        }
    }

    internal fun onCreateComponents() {
        mHandler = Handler()
        ownAd = findViewById(R.id.adView) as AppCompatImageView
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        onSectionAttached(item.itemId)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            this.closeApp()
        }
    }

    internal fun closeApp() {
        val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> this@MainActivity.finish()

                DialogInterface.BUTTON_NEGATIVE -> {
                }
            }
        }

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setMessage(R.string.confirm_exit).setPositiveButton(android.R.string.yes, dialogClickListener)
                .setNegativeButton(android.R.string.no, dialogClickListener).show()
    }

    internal fun openMovies() {
        fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, MovieFragment.newInstance())
                .commit()
    }

    internal fun openTickets() {
        fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, OnlineFragment.newInstance("tickets"))
                .commit()
    }

    internal fun openPromos() {
        fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, OnlineFragment.newInstance("promocoes"))
                .commit()
    }

    internal fun openBomboniere() {
        fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, OnlineFragment.newInstance("bomboniere"))
                .commit()
    }

    internal fun openFaq() {
        fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, OnlineFragment.newInstance("faq"))
                .commit()
    }

    internal fun openLocation() {
        setTitle(R.string.map)
        fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, MapFragment.newInstance())
                .commit()
    }

    override fun run() {
        try {
            when (adCount) {
                0 -> {
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_chaplin.png"
                    loadImage()
                    this.adCount = adCount + 1
                    Log.e(CLASS_NAME, "id 0")
                }
                1 -> {
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_batata_minas.png"
                    loadImage()
                    this.adCount = adCount + 1
                    Log.e(CLASS_NAME, "id 1")
                }
                2 -> {
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_cofee_shop.png"
                    loadImage()
                    this.adCount = adCount + 1
                    Log.e(CLASS_NAME, "id 2")
                }
                3 -> {
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_modulo.png"
                    loadImage()
                    this.adCount = adCount + 1
                    Log.e(CLASS_NAME, "id 3")
                }
                4 -> {
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_toys.png"
                    loadImage()
                    this.adCount = 0
                    Log.e(CLASS_NAME, "id 4")
                }
                else -> {
                    this.adUrl = "https://api.patrocine.com.br/static/data/partners/partner_chaplin.png"
                    loadImage()
                }
            }
        } finally {
            // 100% guarantee that this always happens, even if
            // your update method throws an exception
            mHandler!!.postDelayed(this, mInterval.toLong())
        }

    }

    internal fun loadImage() {
        Picasso.get()
                .load(this.adUrl)
                .resize(320, 50)
                .into(ownAd)
    }

    internal fun startRepeatingTask() {
        this.run()
    }

    internal fun stopRepeatingTask() {
        mHandler!!.removeCallbacks(this)
    }

    override fun onNavigationDrawerItemSelected(position: Int) {

    }

    override fun onFragmentInteraction(obj: Any) {
        val movie = obj as Movie
        val i = Intent(this, MovieDetailsActivity::class.java)
        val b = Bundle()
        b.putSerializable("movie", movie)
        i.putExtras(b)
        startActivity(i)
    }

    fun setupNavigation() {

    }

    companion object {

        private val CLASS_NAME = MainActivity::class.java.simpleName
    }
}