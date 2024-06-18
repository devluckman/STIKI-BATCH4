package com.man.filmku.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.man.filmku.domain.model.movie.MovieData
import com.man.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _nowPlayingData = MutableLiveData<List<MovieData>>()
    val nowPlayingData = _nowPlayingData
    private fun getNowPlaying() {
        viewModelScope.launch {
            repository.getNowPlayingMovie().collect {
                _nowPlayingData.value = it
            }
        }
    }

    private val _popularData = MutableLiveData<List<MovieData>>()
    val popularData = _popularData
    private fun getPopular() {
        viewModelScope.launch {
            repository.getPopularMovie().collect {
                _popularData.value = it
            }
        }
    }

    fun logout() {
        repository.logout()
    }

    init {
        getNowPlaying()
        getPopular()
    }

}