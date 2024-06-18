package com.man.filmku.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.man.filmku.data.database.entity.EntityMovie
import com.man.filmku.domain.model.movie.CastMovieData
import com.man.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _detailDataMovie = MutableLiveData<EntityMovie>()
    val detailDataMovie = _detailDataMovie
    fun getDetailMovie(id : Int) {
        viewModelScope.launch {
            repository.getDetailMovie(id).collect {
                _detailDataMovie.value = it
            }
        }
    }

    private val _castMovieData = MutableLiveData<List<CastMovieData>>()
    val castMovieData = _castMovieData
    fun getCastMovie(id : Int) {
        viewModelScope.launch {
            repository.getCast(id).collect {
                _castMovieData.value = it
            }
        }
    }

    private val _favoriteState = MutableLiveData<Boolean>()
    val favoriteState = _favoriteState
    fun updateDataFavorite(data: EntityMovie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateOrDeleteMovieInFavorite(data).collect {
                _favoriteState.postValue(it)
            }
        }
    }

}