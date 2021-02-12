package com.android.kpopdance.ui

import android.content.ContentUris
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.kpopdance.R
import com.android.kpopdance.contract.Contract
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.fragment_album.*

class AlbumFragment : Fragment() {
    private lateinit var exoPlayer: SimpleExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val youtubeId = arguments?.getString(Contract.ID, "")

        val query = activity?.contentResolver?.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Video.Media._ID),
            "${MediaStore.Video.Media.DISPLAY_NAME} LIKE ?",
            arrayOf("%$youtubeId%"),
            "${MediaStore.Video.Media.DATE_ADDED} DESC")

        val mediaItems: ArrayList<MediaItem> = arrayListOf()

        query?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val contentUri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)
                val mediaItem = MediaItem.Builder()
                    .setUri(contentUri)
                    .setMediaMetadata(MediaMetadata.Builder().setTitle("TAG").build())
                    .build()
                mediaItems.add(mediaItem)
            }
        }

        exoPlayer = SimpleExoPlayer.Builder(context!!).build()

        if (mediaItems.isEmpty()) {
            textView.visibility = View.VISIBLE
            exoPlayerView.visibility = View.INVISIBLE
        } else {
            textView.visibility = View.INVISIBLE
            exoPlayerView.player = exoPlayer
            exoPlayer.setMediaItems(mediaItems)
            exoPlayer.playWhenReady = false
            exoPlayer.prepare()
            exoPlayerView.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        exoPlayerView.player = null
        exoPlayer.release()
    }
}