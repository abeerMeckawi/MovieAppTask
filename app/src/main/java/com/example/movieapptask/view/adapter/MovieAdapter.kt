package com.example.movieapptask.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapptask.R
import com.example.movieapptask.databinding.MovieRowBinding
import com.example.movieapptask.model.network.APIClient
import com.example.movieapptask.model.pojos.Movie
import com.example.movieapptask.view.fragments.MoviesFragmentDirections

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = ArrayList<Movie>()

    fun setData(movieList: ArrayList<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount() = movieList.size


    inner class MovieViewHolder(private val binding: MovieRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            Glide.with(binding.imgMovie)
                .load(APIClient.BASE_IMG_URL + item.imgPath)
                .error(R.drawable.no_image)
                .into(binding.imgMovie)
            binding.movie = item
            binding.movieContainer.setOnClickListener{ view ->
                val action = item.id?.let { it ->
                    MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(it)
                }
                action?.let { view.findNavController().navigate(it) }
            }
        }
    }
}

