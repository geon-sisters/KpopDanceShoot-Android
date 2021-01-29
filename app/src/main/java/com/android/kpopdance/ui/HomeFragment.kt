package com.android.kpopdance.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.kpopdance.contract.Contract
import com.android.kpopdance.R
import com.android.kpopdance.databinding.HomeFragmentBinding
import com.android.kpopdance.viewmodel.HomeViewModel
import com.android.kpopdance.viewmodel.eventObserve
import org.koin.androidx.viewmodel.ext.android.getViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        val vewModel: HomeViewModel = getViewModel()
        vewModel.clickedYoutubeId.eventObserve(this) { youtubeId ->
            val intent = Intent(activity, DanceActivity::class.java)
            intent.putExtra(Contract.ID, youtubeId)
            startActivity(intent)
        }

        binding.vm = vewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}