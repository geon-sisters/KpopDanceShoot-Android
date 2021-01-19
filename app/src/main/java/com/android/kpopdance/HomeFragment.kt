package com.android.kpopdance

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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

        val youtubeBigAdapter = YoutubeBigAdapter()
        youtubeBigAdapter.youtubes = youtubes

        homeRecyclerView.adapter = youtubeBigAdapter
        homeRecyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }
}