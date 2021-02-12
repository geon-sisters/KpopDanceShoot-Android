package com.your.kpopdance.ui

import android.content.Intent
import androidx.fragment.app.Fragment
import com.your.kpopdance.contract.Contract
import com.your.kpopdance.data.Youtube

open class BaseFragment : Fragment()  {
    fun startDanceActivity(youtube: Youtube) {
        val intent = Intent(activity, DanceActivity::class.java)
        intent.putExtra(Contract.ID, youtube.id)
        intent.putExtra(Contract.TITLE, youtube.title)
        startActivity(intent)
    }
}