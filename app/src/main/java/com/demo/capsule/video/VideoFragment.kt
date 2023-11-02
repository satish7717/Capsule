package com.demo.capsule.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.demo.capsule.base.BaseFragment
import com.demo.capsule.databinding.VideoLayoutBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VideoFragment : BaseFragment<VideoFragmentViewModel, VideoLayoutBinding>(){

    override val mViewModel: VideoFragmentViewModel by viewModels()
    private var isSavedState: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.andExoPlayerView.setSource("https://budepublicimages.s3.ap-south-1.amazonaws.com/bud-e-video-demo.mp4")
    }

    override fun getViewBinding(): VideoLayoutBinding =
        VideoLayoutBinding.inflate(layoutInflater)

    override fun onPause() {
        super.onPause()
        mViewBinding.andExoPlayerView.pausePlayer()
    }


}