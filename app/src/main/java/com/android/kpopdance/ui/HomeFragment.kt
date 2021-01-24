package com.android.kpopdance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.kpopdance.R
import com.android.kpopdance.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
        return binding.root
    }
}