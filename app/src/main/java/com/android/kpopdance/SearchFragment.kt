package com.android.kpopdance

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

        // for test
        val youtubes = arrayListOf(
            Youtube(
                "noEhgQ0hI6M",
                "[RAIN - Switch to me (duet with JWP)] dance practice mirrored",
                "2021.01.16"
            ),
            Youtube(
                "jHW2yLQddNw",
                "[TWICE - CRY FOR ME] dance practice mirrored",
                "2021.01.16"
            ),
            Youtube(
                "scVCHsusm4c",
                "[IZ*ONE - Sequence] dance practice mirrored",
                "2021.01.16"
            ),
            Youtube(
                "noEhgQ0hI6M",
                "[RAIN - Switch to me (duet with JWP)] dance practice mirrored",
                "2021.01.16"
            ),
            Youtube(
                "jHW2yLQddNw",
                "[TWICE - CRY FOR ME] dance practice mirrored",
                "2021.01.16"
            ),
            Youtube(
                "scVCHsusm4c",
                "[IZ*ONE - Sequence] dance practice mirrored",
                "2021.01.16"
            ),
            Youtube(
                "noEhgQ0hI6M",
                "[RAIN - Switch to me (duet with JWP)] dance practice mirrored",
                "2021.01.16"
            ),
            Youtube(
                "jHW2yLQddNw",
                "[TWICE - CRY FOR ME] dance practice mirrored",
                "2021.01.16"
            ),
            Youtube(
                "noEhgQ0hI6M",
                "[RAIN - Switch to me (duet with JWP)] dance practice mirrored",
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