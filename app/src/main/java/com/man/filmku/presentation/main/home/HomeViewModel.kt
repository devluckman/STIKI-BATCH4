package com.man.filmku.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.man.filmku.domain.model.movie.MovieData
import com.man.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _nowShowingMovies = MutableLiveData<List<MovieData>>()
    val nowShowingMovies : LiveData<List<MovieData>> = _nowShowingMovies

    fun getNowShowing() {
        // TODO Action get to API
        _nowShowingMovies.value = repository.getMovieNowShowing()
    }

    private val _nowPopularMovies = MutableLiveData<List<MovieData>>()
    val nowPopularMovies : LiveData<List<MovieData>> = _nowPopularMovies

    fun getPopular() {
        // TODO Action get to API
        _nowPopularMovies.value = repository.getMoviePopular()
    }

}