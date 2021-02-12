package com.android.kpopdance.ui

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.kpopdance.R
import com.android.kpopdance.contract.Contract
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.VideoResult
import kotlinx.android.synthetic.main.fragment_camera.*

class CameraFragment : Fragment(), View.OnClickListener {
    private val TAG = Contract.YOUR_KDANCE + CameraFragment::class.simpleName

    private lateinit var resolver: ContentResolver
    private var youtubeId: String = ""
    private var youtubeTitle: String = ""
    private var isVideoRecording: Boolean = false
    private var uri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resolver = activity!!.contentResolver
        youtubeId = arguments!!.getString(Contract.ID, "")
        youtubeTitle = arguments!!.getString(Contract.TITLE, "")

        camera.setLifecycleOwner(viewLifecycleOwner)
        camera.addCameraListener(Listener())
        recordButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.recordButton -> recordVideo()
        }
    }

    private inner class Listener : CameraListener() {
        override fun onVideoRecordingStart() {
            super.onVideoRecordingStart()
            Log.i(TAG, "onVideoRecordingStart")
        }

        override fun onVideoRecordingEnd() {
            super.onVideoRecordingEnd()
            recordButton.setImageResource(R.mipmap.ic_record)
            isVideoRecording = false
        }

        override fun onVideoTaken(result: VideoResult) {
            super.onVideoTaken(result)
            Log.i(TAG, "onVideoTaken")
            Toast.makeText(activity, "저장되었습니다", Toast.LENGTH_SHORT).show()
            uri = null
        }
    }

    private fun recordVideo() {
        if (isVideoRecording) {
            camera.stopVideo()
        } else {
            val videoCollection = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            val videoDetails = ContentValues().apply {
                put(MediaStore.Video.Media.DISPLAY_NAME, youtubeId + "_" + youtubeTitle)
                put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
            }

            uri = resolver.insert(videoCollection, videoDetails)
            val videoFileDescriptor = uri?.let { resolver.openFileDescriptor(it, "w", null)?.fileDescriptor }
            if (videoFileDescriptor != null) {
                camera.takeVideo(videoFileDescriptor)
                recordButton.setImageResource(R.mipmap.ic_stop)
                isVideoRecording = true
            } else {
                Toast.makeText(activity, "실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}