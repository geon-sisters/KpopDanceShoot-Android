package com.android.kpopdance.ui

import android.content.Intent
import androidx.fragment.app.Fragment
import com.android.kpopdance.contract.Contract

open class BaseFragment : Fragment()  {
    fun startDanceActivity(youtubeId: String) {
        val intent = Intent(activity, DancePortraitActivity::class.java)
        intent.putExtra(Contract.ID, youtubeId)
        startActivity(intent)
    }
}