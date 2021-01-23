package com.android.kpopdance.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.kpopdance.R
import com.android.kpopdance.viewmodel.SearchViewModel
import com.android.kpopdance.data.Youtube
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : Fragment() {
    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adView_search.loadAd(AdRequest.Builder().build())

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


        val youtubeSmallAdapter = YoutubeSmallAdapter()
        youtubeSmallAdapter.youtubes = youtubes

        searchRecyclerView.adapter = youtubeSmallAdapter
        searchRecyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        activity?.findViewById<ImageButton>(R.id.mainToolbarButton)?.setOnClickListener {
            if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                drawer.closeDrawer(Gravity.RIGHT)
            } else {
                drawer.openDrawer(Gravity.RIGHT)
            }
        }


        val singerAdapter = SingerAdapter()
        // for test
        singerAdapter.singers = arrayListOf("Itzy", "BlackPink", "Itzy", "BlackPink", "Itzy", "BlackPink", "Itzy", "BlackPink", "Itzy", "BlackPink", "Itzy", "BlackPink", "EXO")
        singerRecyclerView.adapter = singerAdapter
        singerRecyclerView.layoutManager = LinearLayoutManager(this.context)
    }
}