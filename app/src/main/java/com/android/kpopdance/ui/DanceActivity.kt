package com.android.kpopdance.ui

import android.os.Bundle
import android.util.Log
import com.android.kpopdance.R
import com.android.kpopdance.contract.Contract
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class DanceActivity : YouTubeBaseActivity() {
    private val TAG = Contract.K_POP_DANCE + DanceActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dance)

        val youtubeId = intent.getStringExtra(Contract.ID) ?: ""
        Log.d(TAG, youtubeId)

        val danceYoutube = findViewById<YouTubePlayerView>(R.id.danceYoutube)
        danceYoutube.initialize("develop", object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                player: YouTubePlayer,
                wasRestored: Boolean
            ) {
                if (!wasRestored) {
                    player!!.cueVideo(youtubeId)
                }
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) {
                Log.d(TAG, "Failed to initialize")
            }
        })
    }
}