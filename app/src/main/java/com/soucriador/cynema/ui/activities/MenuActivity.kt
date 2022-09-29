package com.soucriador.cynema.ui.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.soucriador.cynema.R
import com.soucriador.cynema.model.Movie
import com.soucriador.cynema.ui.fragments.MapFragment
import com.soucriador.cynema.ui.fragments.MovieFragment
import com.soucriador.cynema.ui.fragments.OnlineFragment
import com.soucriador.cynema.ui.interfaces.OnFragmentInteractionListener
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.app_bar_menu.*

class MenuActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        openMovies()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navMovies -> {
                openMovies()
            }
            R.id.navPromos -> {
                openPromos()
            }

            R.id.navLoyalty -> {
                openLoyalty()
            }

            R.id.navBomboniere -> {
                openBomboniere()
            }

            R.id.navFaq -> {
                openFaq()
            }

            R.id.navTickets -> {
                openTickets()
            }

            R.id.navLocation -> {
                openLocation()
            }

            R.id.navClose -> {
                closeApp()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    internal fun openMovies() {
        title = getString(R.string.title_movies)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MovieFragment.newInstance())
                .commit()
    }

    internal fun openTickets() {
        title = getString(R.string.title_tickets)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, com.soucriador.cynema.ui.fragments.OnlineFragment.newInstance("tickets"))
                .commit()
    }

    internal fun openLoyalty() {
        title = getString(R.string.fidelidade)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, com.soucriador.cynema.ui.fragments.OnlineFragment.newInstance("fidelidade"))
                .commit()
    }

    internal fun openPromos() {
        title = getString(R.string.promotions)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, com.soucriador.cynema.ui.fragments.OnlineFragment.newInstance("promocoes"))
                .commit()
    }

    internal fun openBomboniere() {
        title = getString(R.string.title_bomboniere)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, com.soucriador.cynema.ui.fragments.OnlineFragment.newInstance("bomboniere"))
                .commit()
    }

    internal fun openFaq() {
        title = getString(R.string.title_faq)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, com.soucriador.cynema.ui.fragments.OnlineFragment.newInstance("faq"))
                .commit()
    }

    internal fun openLocation() {
        title = getString(R.string.title_location)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, com.soucriador.cynema.ui.fragments.MapFragment.newInstance())
                .commit()
    }

    fun closeApp() {
        val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> this@MenuActivity.finish()

                DialogInterface.BUTTON_NEGATIVE -> {
                }
            }
        }

        val builder = AlertDialog.Builder(this@MenuActivity)
        builder.setMessage(R.string.confirm_exit).setPositiveButton(android.R.string.yes, dialogClickListener)
                .setNegativeButton(android.R.string.no, dialogClickListener).show()
    }

    override fun onFragmentInteraction(obj: Any) {
        val movie = obj as Movie
        val i = Intent(this, MovieDetailsOldActivity::class.java)
        val b = Bundle()
        b.putSerializable("movie", movie)
        i.putExtras(b)
        startActivity(i)
    }
}
