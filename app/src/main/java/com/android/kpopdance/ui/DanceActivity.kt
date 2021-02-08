package com.android.kpopdance.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.kpopdance.R
import com.android.kpopdance.contract.Contract
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dance.*
import kr.co.prnd.YouTubePlayerView


class DanceActivity: AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    companion object {
        private val TAG = Contract.YOUR_KDANCE + DanceActivity::class.simpleName
        private const val SELECTED_FRAGMENT: String = "SELECTED_FRAGMENT"
    }

    private var selectedFragment = 0
    private var youtubeId: String = ""
    private var youtubeTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dance)

        Log.d(TAG, youtubeId)
        youtubeId = intent.getStringExtra(Contract.ID) ?: ""
        youtubeTitle = intent.getStringExtra(Contract.TITLE) ?: ""

        val youTubePlayerView: YouTubePlayerView = findViewById(R.id.youtubePlayerView)
        youTubePlayerView.play(youtubeId)

        danceNavigationView.setOnNavigationItemSelectedListener(this)

        selectedFragment = savedInstanceState?.getInt(SELECTED_FRAGMENT, 0)?:0
        if (selectedFragment == 0) {
            supportFragmentManager.beginTransaction().replace(R.id.danceFrameLayout, getCameraFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.danceFrameLayout, getAlbumFragment()).commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_FRAGMENT, selectedFragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dance_camera -> {
                Log.i(TAG, "camera")
                supportFragmentManager.beginTransaction().replace(R.id.danceFrameLayout, getCameraFragment()).commit()
                selectedFragment = 0
            }
            R.id.dance_album -> {
                Log.i(TAG, "album")
                supportFragmentManager.beginTransaction().replace(R.id.danceFrameLayout, getAlbumFragment()).commit()
                selectedFragment = 1
            }
        }
        return true
    }

    private fun getCameraFragment(): CameraFragment {
        val cameraFragment = CameraFragment()
        cameraFragment.arguments = getBundle()
        return cameraFragment
    }

    private fun getAlbumFragment(): AlbumFragment {
        val albumFragment = AlbumFragment()
        albumFragment.arguments = getBundle()
        return albumFragment
    }

    private fun getBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(Contract.ID, youtubeId)
        bundle.putString(Contract.TITLE, youtubeTitle)
        return bundle
    }
}