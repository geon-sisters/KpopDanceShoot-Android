package com.android.kpopdance.ui

import android.content.Intent
import androidx.fragment.app.Fragment
import com.android.kpopdance.contract.Contract
import com.android.kpopdance.data.Youtube

open class BaseFragment : Fragment()  {
    fun startDanceActivity(youtube: Youtube) {
        val intent = Intent(activity, DanceActivity::class.java)
        intent.putExtra(Contract.ID, youtube.id)
        intent.putExtra(Contract.TITLE, youtube.title)
        startActivity(intent)
    }
}