package com.android.kpopdance.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.kpopdance.R
import com.android.kpopdance.contract.Contract
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val TAG = Contract.YOUR_KDANCE + MainActivity::class.simpleName
    private val homeFragment = HomeFragment()
    private val bookmarkFragment = BookmarkFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, LoadingActivity::class.java)
        startActivity(intent)
        MobileAds.initialize(this) {}

        setSupportActionBar(findViewById(R.id.mainToolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, homeFragment).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_home -> {
                Log.i(TAG, "home")
                mainToolbarTextView.setText(R.string.app_name)
                mainToolbarButton.setVisibility(View.INVISIBLE)
                supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, homeFragment).commit()
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
                supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, bookmarkFragment).commit()
            }
        }
        return true
    }

    override fun onBackPressed(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Thank you")
        builder.setMessage("Do you really want to close the app?")
        builder.setPositiveButton("close") { _: DialogInterface?, _: Int -> finish()}
        builder.setNegativeButton("cancel", null)
        builder.show()
    }
}