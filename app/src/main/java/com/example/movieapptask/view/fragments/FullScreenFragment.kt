package com.example.movieapptask.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapptask.R
import com.example.movieapptask.databinding.FragmentFullScreenBinding
import com.example.movieapptask.model.network.APIClient


class FullScreenFragment : Fragment() {

    private lateinit var binding: FragmentFullScreenBinding
    private val args: FullScreenFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFullScreenBinding.inflate(inflater, container, false)

        val fullScreenImagePath = args.imgPath
        val movieID = args.movieID

        Glide.with(binding.fullScreenImage)
            .load(APIClient.BASE_IMG_URL + fullScreenImagePath)
            .error(R.drawable.no_image)
            .into(binding.fullScreenImage)


        binding.fullScreenContainer.setOnClickListener {
            val action =
                FullScreenFragmentDirections.actionFullScreenFragmentToDetailsFragment(movieID)
            it.findNavController().navigate(action)
        }
        return binding.root
    }

}