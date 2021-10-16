package com.example.movieapptask.utils

import android.view.View
import androidx.navigation.findNavController
import com.example.movieapptask.model.pojos.Crew
import com.example.movieapptask.model.pojos.HandleDataStatus
import com.example.movieapptask.view.fragments.DetailsFragment
import com.example.movieapptask.view.fragments.DetailsFragmentDirections
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.snackbar.Snackbar
import kotlin.math.abs


fun DetailsFragment.handleCrewData(crewData : ArrayList<Crew>){
    val directoryName = crewData.find { it.job == "Director"|| it.job =="Screenplay" }?.crewName
    binding.tvDirectoryName.text = directoryName
    val jobsDirector = crewData.filter { it.crewName.equals(directoryName)}
    val jobListOfDirectors = arrayListOf<String>()
    for (item in jobsDirector){
        item.job?.let { jobListOfDirectors.add(it) }
    }
    binding.tvDirectoryType.text = jobListOfDirectors.joinToString(", ")

    val writerName = crewData.find { it.job == "Writer" || it.job == "Novel" ||it.job =="Story"  }?.crewName
    binding.tvWriterName.text = writerName
    val jobsWriter = crewData.filter { it.crewName.equals(writerName)}
    val jobListOfWriters = arrayListOf<String>()
    for (item in jobsWriter){
        item.job?.let { jobListOfWriters.add(it) }
    }
    binding.tvWriterType.text = jobListOfWriters.joinToString(", ")

    if (directoryName.equals(writerName)){
        binding.tvWriterName.visibility=View.INVISIBLE
        binding.tvWriterType.visibility=View.INVISIBLE
    }
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


fun DetailsFragment.handleFullScreenFragment(imgPath: String, movieID: Int){
    binding.detailsImg.setOnClickListener {
        val action = DetailsFragmentDirections.actionDetailsFragmentToFullScreenFragment(imgPath,movieID)
        it.findNavController().navigate(action)
    }

}

fun DetailsFragment.hideImageWhenCollapse(){
    binding.appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
        if (abs(verticalOffset) == appBarLayout.totalScrollRange) {
            binding.detailsImg.visibility = View.INVISIBLE
        }else if(verticalOffset == 0){
            binding.detailsImg.visibility = View.VISIBLE
        }
    })
}