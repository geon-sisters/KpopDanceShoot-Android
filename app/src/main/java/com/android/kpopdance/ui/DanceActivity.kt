package com.android.kpopdance.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.android.kpopdance.R
import com.android.kpopdance.contract.Contract
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dance.*
import kr.co.prnd.YouTubePlayerView


class DanceActivity: AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private companion object {
        val TAG = Contract.YOUR_KDANCE + DanceActivity::class.simpleName
        const val SELECTED_FRAGMENT: String = "SELECTED_FRAGMENT"
        const val CAMERA = 0
        const val ALBUM = 1
        const val REQUEST_CODE = 100
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

        selectedFragment = savedInstanceState?.getInt(SELECTED_FRAGMENT, CAMERA)?:CAMERA
        if (selectedFragment == CAMERA) {
            supportFragmentManager.beginTransaction().replace(R.id.danceFrameLayout, getCameraFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.danceFrameLayout, getAlbumFragment()).commit()
        }

        permissionCheck()
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
                selectedFragment = CAMERA
            }
            R.id.dance_album -> {
                Log.i(TAG, "album")
                supportFragmentManager.beginTransaction().replace(R.id.danceFrameLayout, getAlbumFragment()).commit()
                selectedFragment = ALBUM
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE && grantResults.firstOrNull{it != PackageManager.PERMISSION_GRANTED} != null) {
            Toast.makeText(this, "Please allow permissions.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun permissionCheck(){
        val permissionRequests: ArrayList<String> = arrayListOf()

        if (android.os.Build.VERSION.SDK_INT <= 28
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionRequests.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            permissionRequests.add(Manifest.permission.RECORD_AUDIO)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionRequests.add(Manifest.permission.CAMERA)
        }
        if (permissionRequests.isNotEmpty()) {
            requestPermissions(permissionRequests.toTypedArray(), REQUEST_CODE)
        }
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