package br.com.patrocine.patrocine.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import br.com.patrocine.patrocine.R
import br.com.patrocine.patrocine.ui.fragments.tabexamples.*
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList

class MovieDetailsActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details_tabbed)

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById<View>(R.id.viewpager) as ViewPager?
        setupViewPager(viewPager!!)

        tabLayout = findViewById<View>(R.id.tabs) as TabLayout?
        tabLayout!!.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(FragmentOne(), "ONE")
        adapter.addFrag(FragmentTwo(), "TWO")
        adapter.addFrag(FragmentThree(), "THREE")
        adapter.addFrag(FragmentFour(), "FOUR")
        adapter.addFrag(FragmentFive(), "FIVE")
        adapter.addFrag(FragmentSix(), "SIX")
        adapter.addFrag(FragmentSeven(), "SEVEN")
        adapter.addFrag(FragmentEight(), "EIGHT")
        adapter.addFrag(FragmentNine(), "NINE")
        adapter.addFrag(FragmentTen(), "TEN")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}