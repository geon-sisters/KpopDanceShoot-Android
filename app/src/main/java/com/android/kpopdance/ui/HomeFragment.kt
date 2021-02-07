package com.android.kpopdance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.kpopdance.R
import com.android.kpopdance.databinding.HomeFragmentBinding
import com.android.kpopdance.viewmodel.HomeViewModel
import com.android.kpopdance.viewmodel.eventObserve
import org.koin.androidx.viewmodel.ext.android.getViewModel


class HomeFragment : BaseFragment() {
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        val viewModel: HomeViewModel = getViewModel()
        viewModel.clickedYoutube.eventObserve(this) { youtube -> startDanceActivity(youtube) }

        binding.vm = viewModel
        binding.lifecycleOwner = this.activity
        return binding.root
    }
}