package com.example.movieapptask.utils

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.movieapptask.R
import com.example.movieapptask.model.pojos.Crew
import com.example.movieapptask.model.pojos.HandleDataStatus
import com.example.movieapptask.view.fragments.DetailsFragment
import com.google.android.material.snackbar.Snackbar


fun DetailsFragment.handleCrewData(crewData : ArrayList<Crew>){
    val directoryName = crewData.find { it.job == "Director" }?.crewName
    binding.tvDirectoryName.text = directoryName
    binding.tvDirectoryType.text = getString(R.string.director)

    val writerName = crewData.find { it.job == "Writer" }?.crewName
    binding.tvWriterName.text = writerName
    binding.tvWriterType.text = getString(R.string.writer)
}


fun DetailsFragment.handleDataStatus() = object:HandleDataStatus{

    override fun onLoading() {
        binding.detailsProgressBar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        binding.detailsProgressBar.visibility = View.INVISIBLE
    }

    override fun onFailed() {
        binding.detailsProgressBar.visibility = View.INVISIBLE
        Snackbar.make(binding.root.rootView,"Not Data Found", Snackbar.LENGTH_SHORT).show()
    }
}


fun DetailsFragment.fullScreenFragment(){
    binding.detailsImg.setOnClickListener {
        zoomOut = if (zoomOut) {
            binding.detailsImg.setLayoutParams(
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            )
            binding.detailsImg.adjustViewBounds = true
            false
        } else {
            binding.detailsImg.setLayoutParams(
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            )
            binding.detailsImg.scaleType = ImageView.ScaleType.FIT_XY
            true
        }
    }

}