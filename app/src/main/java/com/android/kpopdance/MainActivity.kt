package com.android.kpopdance

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    companion object {
        private val TAG = "[KPopDance]" + MainActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        setSupportActionBar(findViewById(R.id.mainToolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, HomeFragment()).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_home -> {
                Log.i(TAG, "home")
                mainToolbarTextView.setText(R.string.home)
                mainToolbarButton.setVisibility(View.INVISIBLE)
                supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, HomeFragment()).commit()
            }
            R.id.bottom_search -> {
                Log.i(TAG, "search")
                mainToolbarTextView.setText(R.string.search)
                mainToolbarButton.setVisibility(View.VISIBLE)
                supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, SearchFragment()).commit()
            }
            R.id.bottom_bookmark -> {
                Log.i(TAG, "bookmark")
                mainToolbarTextView.setText(R.string.bookmark)
                mainToolbarButton.setVisibility(View.INVISIBLE)
                supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout,
                    BookmarkFragment()
                ).commit()
            }
        }
        return true
    }
}