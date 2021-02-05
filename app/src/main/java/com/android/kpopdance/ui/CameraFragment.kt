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

        override fun onVideoTaken(result: VideoResult) {
            super.onVideoTaken(result)
            Log.i(TAG, "onVideoTaken")
            val fileDetails = ContentValues().apply {
                put(MediaStore.Video.Media.IS_PENDING, 0)
            }
            resolver.update(uri!!, fileDetails, null, null)
            Toast.makeText(activity, "저장되었습니다", Toast.LENGTH_SHORT).show()
            uri = null
        }
    }

    private fun recordVideo() {
        if (isVideoRecording) {
            val videoCollection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            val videoDetails = ContentValues().apply {
                put(MediaStore.Video.Media.DISPLAY_NAME, youtubeId)
                put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
                put(MediaStore.Video.Media.IS_PENDING, 1)
            }

            uri = resolver.insert(videoCollection, videoDetails)
            val videoFileDescriptor = resolver.openFileDescriptor(uri!!, "w", null)?.fileDescriptor
            if (videoFileDescriptor != null) {
                camera.takeVideo(videoFileDescriptor)
                recordButton.setImageResource(R.mipmap.ic_stop)
                isVideoRecording = false
            } else {
                Toast.makeText(activity, "실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        } else {
            camera.stopVideo()
            recordButton.setImageResource(R.mipmap.ic_record)
            isVideoRecording = true
        }
    }
}