package com.example.movieapptask.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.movieapptask.databinding.ActivityMainBinding
import com.example.movieapptask.viewmodel.SearchMovieVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    val searchVM :SearchMovieVM by lazy { ViewModelProvider(this).get(SearchMovieVM::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}