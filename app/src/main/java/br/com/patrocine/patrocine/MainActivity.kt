package br.com.patrocine.patrocine

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.patrocine.patrocine.views.fragments.MoviesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Compartilhamento nao disponivel", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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
        menuInflater.inflate(R.menu.main, menu)
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
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_news -> {
                Toast.makeText(this, "Promoções e Novidades", Toast.LENGTH_SHORT).show();
            }
            R.id.nav_sessions -> {
                Toast.makeText(this, "Em exibição", Toast.LENGTH_SHORT).show();
                val fragmentManager = supportFragmentManager
                fragmentManager.beginTransaction()
                        .replace(R.id.container, MoviesFragment.newInstance("", ""))
                        .commit()
            }
            R.id.nav_contact -> {
                Toast.makeText(this, "Redes Sociais", Toast.LENGTH_SHORT).show();
            }
            R.id.nav_map -> {
                Toast.makeText(this, "Localizacao", Toast.LENGTH_SHORT).show();

            }
            R.id.nav_share -> {
                Toast.makeText(this, "Compartilhamento", Toast.LENGTH_SHORT).show();
            }
            R.id.nav_send -> {
                Toast.makeText(this, "Clicou em enviar", Toast.LENGTH_SHORT).show();
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
