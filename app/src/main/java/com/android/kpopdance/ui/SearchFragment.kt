package com.android.kpopdance.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import com.android.kpopdance.R
import com.android.kpopdance.databinding.SearchFragmentBinding
import com.android.kpopdance.viewmodel.SearchViewModel
import com.android.kpopdance.viewmodel.eventObserve
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class SearchFragment : BaseFragment() {
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)

        val viewModel: SearchViewModel = getViewModel()
        viewModel.clickedYoutube.eventObserve(this) { youtube -> startDanceActivity(youtube)}
        viewModel.clickedQuickSearch.eventObserve(this) {
            searchEditText.setText(it)
            viewModel.onSearchButtonClicked()
            if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                drawer.closeDrawer(Gravity.RIGHT)
            }
        }

        binding.vm = viewModel
        binding.lifecycleOwner = this.activity
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adView_search.loadAd(AdRequest.Builder().build())

        activity?.findViewById<ImageButton>(R.id.mainToolbarButton)?.setOnClickListener {
            if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                quickSearchRecyclerView.scrollToPosition(0)
                drawer.closeDrawer(Gravity.RIGHT)
            } else {
                drawer.openDrawer(Gravity.RIGHT)
            }
        }
    }
}