package com.your.kpopdance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.your.kpopdance.R
import com.your.kpopdance.databinding.HomeFragmentBinding
import com.your.kpopdance.viewmodel.HomeViewModel
import com.your.kpopdance.viewmodel.eventObserve
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class HomeFragment : BaseFragment(), View.OnClickListener {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.upButton -> {
                homeRecyclerView.smoothScrollToPosition(0)
            }
        }
    }
}