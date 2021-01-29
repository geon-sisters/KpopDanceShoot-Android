package com.android.kpopdance.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.kpopdance.contract.Contract
import com.android.kpopdance.R

class DanceActivity : AppCompatActivity() {
    private val TAG = "[KPopDance]" + DanceActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dance)

        val youtubeId = intent.getStringExtra(Contract.ID)?:""

        Log.d(TAG, youtubeId)
    }
}