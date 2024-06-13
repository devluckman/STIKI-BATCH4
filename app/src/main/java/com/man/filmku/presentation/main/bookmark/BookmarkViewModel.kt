package com.man.filmku.presentation.main.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.man.filmku.domain.model.movie.MovieData
import com.man.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _bookmarkMovies = MutableLiveData<List<MovieData>>()
    val bookmarkMovie : LiveData<List<MovieData>> = _bookmarkMovies

    fun getBookmark() {
        // TODO Action get to API
        _bookmarkMovies.value = repository.getMoviePopular()
    }

}