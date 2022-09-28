package br.com.soucriador.cynema.cynema.ui.fragments

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import br.com.patrocine.cynema.BuildConfig
import br.com.patrocine.cynema.R
import br.com.patrocine.cynema.ui.interfaces.NavigationDrawerCallbacks


class NavigationDrawerFragment : Fragment() {
    private var mCallbacks: NavigationDrawerCallbacks? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null

    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerListView: ListView? = null
    private var mFragmentContainerView: View? = null

    private var mCurrentSelectedPosition = 0
    private var mFromSavedInstanceState: Boolean = false
    private var mUserLearnedDrawer: Boolean = false

    val isDrawerOpen: Boolean
        get() = mDrawerLayout != null && mDrawerLayout!!.isDrawerOpen(mFragmentContainerView!!)

    private val actionBar: ActionBar?
        get() = activity?.actionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sp = PreferenceManager.getDefaultSharedPreferences(activity)
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false)

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION)
            mFromSavedInstanceState = true
        }

        // Select either the default item (0) or the last selected item.
        selectItem(mCurrentSelectedPosition)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDrawerListView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false) as ListView
        mDrawerListView!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> selectItem(position) }
        mDrawerListView!!.adapter = ArrayAdapter(
                context!!,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                BuildConfig.navigation_menu)
        mDrawerListView!!.setItemChecked(mCurrentSelectedPosition, true)
        return mDrawerListView
    }

    fun setUp(fragmentId: Int, drawerLayout: DrawerLayout) {
        mFragmentContainerView = activity!!.findViewById(fragmentId)
        mDrawerLayout = drawerLayout

        mDrawerLayout!!.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START)

        val actionBar = actionBar
        // actionBar.setDisplayHomeAsUpEnabled(true);
        // actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = object : ActionBarDrawerToggle(
                activity, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.string.navigation_drawer_open, /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                if (!isAdded) {
                    return
                }

                activity!!.invalidateOptionsMenu() // calls onPrepareOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                if (!isAdded) {
                    return
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true
                    val sp = PreferenceManager
                            .getDefaultSharedPreferences(activity)
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply()
                }

                activity!!.invalidateOptionsMenu() // calls onPrepareOptionsMenu()
            }
        }

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout!!.openDrawer(mFragmentContainerView!!)
        }

        mDrawerLayout!!.post { mDrawerToggle!!.syncState() }

        mDrawerLayout!!.addDrawerListener(mDrawerToggle!!)
    }

    private fun selectItem(position: Int) {
        mCurrentSelectedPosition = position
        if (mDrawerListView != null) {
            mDrawerListView!!.setItemChecked(position, true)
        }
        if (mDrawerLayout != null) {
            mDrawerLayout!!.closeDrawer(mFragmentContainerView!!)
        }
        if (mCallbacks != null) {
            mCallbacks!!.onNavigationDrawerItemSelected(position)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mCallbacks = context as NavigationDrawerCallbacks?
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity must implement NavigationDrawerCallbacks.")
        }

    }

    override fun onDetach() {
        super.onDetach()
        mCallbacks = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (mDrawerToggle!!.onOptionsItemSelected(item)) {
            Toast.makeText(activity, "Example action.", Toast.LENGTH_SHORT).show()
            return true
        }

        /*if (item.getItemId() == R.id.action_example) {
            Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT).show();
            return true;
        }*/

        return super.onOptionsItemSelected(item)
    }

    private fun showGlobalContextActionBar() {
        val actionBar = actionBar
        actionBar!!.setDisplayShowTitleEnabled(true)
        actionBar.navigationMode = ActionBar.NAVIGATION_MODE_STANDARD
        actionBar.setTitle(R.string.app_name)
    }

    companion object {

        private val STATE_SELECTED_POSITION = "selected_navigation_drawer_position"
        private val PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned"
    }
}