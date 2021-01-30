package com.android.kpopdance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.kpopdance.R
import com.android.kpopdance.databinding.BookmarkFragmentBinding
import com.android.kpopdance.viewmodel.BookmarkViewModel
import com.android.kpopdance.viewmodel.eventObserve
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.bookmark_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class BookmarkFragment : BaseFragment() {
    private lateinit var binding: BookmarkFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bookmark_fragment, container, false)

        val viewModel: BookmarkViewModel = getViewModel()
        viewModel.clickedYoutubeId.eventObserve(this) { youtubeId -> startDanceActivity(youtubeId) }

        binding.vm = viewModel
        binding.lifecycleOwner = this.activity
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adView_bookmark.loadAd(AdRequest.Builder().build())
    }
}