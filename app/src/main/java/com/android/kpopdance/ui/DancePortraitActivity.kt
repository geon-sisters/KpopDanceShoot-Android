package com.android.kpopdance.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.kpopdance.R
import com.android.kpopdance.contract.Contract
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dance_portrait.*
import kr.co.prnd.YouTubePlayerView


class DancePortraitActivity: AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val TAG = Contract.K_POP_DANCE + DancePortraitActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dance_portrait)

        val youtubeId = intent.getStringExtra(Contract.ID) ?: ""
        Log.d(TAG, youtubeId)

        val youTubePlayerView: YouTubePlayerView = findViewById(R.id.youtubePlayerView)
        youTubePlayerView.play(youtubeId)

        danceNavigationView.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.danceFrameLayout, CameraFragment()).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dance_camera -> {
                Log.i(TAG, "camera")
                supportFragmentManager.beginTransaction().replace(R.id.danceFrameLayout, CameraFragment()).commit()
            }
            R.id.dance_album -> {
                Log.i(TAG, "album")
                supportFragmentManager.beginTransaction().replace(R.id.danceFrameLayout, AlbumFragment()).commit()
            }
        }
        return true
    }
}