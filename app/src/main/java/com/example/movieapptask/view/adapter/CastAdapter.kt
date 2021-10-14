package com.example.movieapptask.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapptask.R
import com.example.movieapptask.databinding.CastRowBinding
import com.example.movieapptask.model.network.APIClient
import com.example.movieapptask.model.pojos.Cast

class CastAdapter :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private var castList = ArrayList<Cast>()

    fun setData(castList: ArrayList<Cast>) {
        this.castList = castList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            CastRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) =
        holder.bind(castList[position])

    override fun getItemCount() = castList.size

    inner class CastViewHolder(private val binding: CastRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cast) {
            Glide.with(binding.imgCast)
                .load(APIClient.BASE_IMG_URL + item.castImg)
                .error(R.drawable.no_image)
                .into(binding.imgCast)
        }
    }
}