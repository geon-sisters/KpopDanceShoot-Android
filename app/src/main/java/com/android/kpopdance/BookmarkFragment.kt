package com.android.kpopdance

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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

        bookmarkRecyclerView.adapter = youtubeSmallAdapter
        bookmarkRecyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)
    }

}
