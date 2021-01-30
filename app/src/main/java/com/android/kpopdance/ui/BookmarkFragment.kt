package com.android.kpopdance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.kpopdance.R
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.viewmodel.BookmarkViewModel
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.bookmark_fragment.*


class BookmarkFragment : Fragment() {
    companion object {
        fun newInstance() = BookmarkFragment()
    }

    private lateinit var viewModel: BookmarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bookmark_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adView_bookmark.loadAd(AdRequest.Builder().build())

        // for test
        val youtubes = arrayListOf(
            Youtube(
                "N-n3hEJKvC4",
                "(G)I-DLE - HWAA Dance Practice (Mirrored)",
                "2021.01.16"
            ),
            Youtube(
                "FPjbtai9rx0",
                "aespa (에스파) - Black Mamba Dance Practice (Mirrored)",
                "2021.01.16"
            ),
            Youtube(
                "nvKDrmGP4Is",
                "TWICE - I CAN'T STOP ME Dance Practice (Mirrored)",
                "2021.01.16"
            ),
            Youtube(
                "RDFJQmNCBoU",
                "ITZY - Not Shy Dance Practice (Mirrored)",
                "2021.01.16"
            ),
            Youtube(
                "N-n3hEJKvC4",
                "(G)I-DLE - HWAA Dance Practice (Mirrored)",
                "2021.01.16"
            ),
            Youtube(
                "FPjbtai9rx0",
                "aespa (에스파) - Black Mamba Dance Practice (Mirrored)",
                "2021.01.16"
            ),
            Youtube(
                "nvKDrmGP4Is",
                "TWICE - I CAN'T STOP ME Dance Practice (Mirrored)",
                "2021.01.16"
            ),
            Youtube(
                "RDFJQmNCBoU",
                "ITZY - Not Shy Dance Practice (Mirrored)",
                "2021.01.16"
            ),
            Youtube(
                "ogGuOZ_6O5c",
                "CHUNG HA (청하) - Roller Coaster Dance Practice (Mirrored)",
                "2021.01.16"
            )
        )

        val bookmarkYoutubeAdapter = BookmarkYoutubeAdapter()
        bookmarkYoutubeAdapter.youtubes = youtubes

        bookmarkRecyclerView.adapter = bookmarkYoutubeAdapter
    }

}
