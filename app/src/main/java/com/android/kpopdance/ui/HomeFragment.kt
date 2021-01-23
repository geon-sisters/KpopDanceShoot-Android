package com.android.kpopdance.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.kpopdance.viewmodel.HomeViewModel
import com.android.kpopdance.R
import com.android.kpopdance.data.Youtube
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

        val youtubeBigAdapter = YoutubeBigAdapter()
        youtubeBigAdapter.youtubes = youtubes

        homeRecyclerView.adapter = youtubeBigAdapter
        homeRecyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }
}