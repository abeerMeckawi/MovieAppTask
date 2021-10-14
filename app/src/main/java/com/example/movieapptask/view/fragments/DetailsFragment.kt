package com.example.movieapptask.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapptask.R
import com.example.movieapptask.databinding.FragmentDetailsBinding
import com.example.movieapptask.model.network.APIClient
import com.example.movieapptask.model.pojos.DataStatus.*
import com.example.movieapptask.utils.fullScreenFragment
import com.example.movieapptask.utils.handleCrewData
import com.example.movieapptask.utils.handleDataStatus
import com.example.movieapptask.view.adapter.CastAdapter
import com.example.movieapptask.viewmodel.MovieDetailsVM
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class DetailsFragment : Fragment() {

    internal lateinit var binding: FragmentDetailsBinding
    private val movieDetailsVM: MovieDetailsVM by lazy { ViewModelProvider(this).get(MovieDetailsVM::class.java) }
    private val args: DetailsFragmentArgs by navArgs()
    private val castAdapter: CastAdapter by lazy { CastAdapter() }
    internal var zoomOut = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val movieID = args.movieID
        movieDetailsVM.getMovieDetails(movieID)
        binding.rvCast.adapter = castAdapter
        lifecycle.addObserver(binding.youtubePlayerVideo)
        fullScreenFragment()

        movieDetailsVM.video.observe(viewLifecycleOwner, { video ->
            if (video.site == "YouTube") {
                binding.youtubePlayerVideo.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {

                        youTubePlayer.cueVideo(video.key.toString(), 0F)
                    }
                })
            }
        })

        movieDetailsVM.movie.observe(viewLifecycleOwner, { movie ->
            val crewData = movie.credits?.crew
            val castData = movie.credits?.cast
            Glide.with(binding.detailsImg)
                .load(APIClient.BASE_IMG_URL + movie.imgPath)
                .error(R.drawable.no_image)
                .into(binding.detailsImg)
            binding.movie = movie

            if (crewData != null && crewData.count() > 0) {
                handleCrewData(crewData)
            }

            if (castData != null && castData.count() > 0) {
                castAdapter.setData(castData)
            }

        })

        movieDetailsVM.dataStatus.observe(viewLifecycleOwner, {
            when (it) {
                LOADING -> handleDataStatus().onLoading()
                SUCCESS -> handleDataStatus().onSuccess()
                FAIL -> handleDataStatus().onFailed()
                else -> return@observe
            }
        })
        return binding.root
    }

}

