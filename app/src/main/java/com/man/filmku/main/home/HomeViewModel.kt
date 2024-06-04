package com.man.filmku.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.man.filmku.model.movie.MovieData

class HomeViewModel : ViewModel() {

    private val _nowShowingMovies = MutableLiveData<List<MovieData>>()
    val nowShowingMovies : LiveData<List<MovieData>> = _nowShowingMovies

    fun getNowShowing() {
        // TODO Action get to API
        _nowShowingMovies.value = MovieData.dummy
    }

    private val _nowPopularMovies = MutableLiveData<List<MovieData>>()
    val nowPopularMovies : LiveData<List<MovieData>> = _nowPopularMovies

    fun getPopular() {
        // TODO Action get to API
        _nowPopularMovies.value = MovieData.dummy
    }

}