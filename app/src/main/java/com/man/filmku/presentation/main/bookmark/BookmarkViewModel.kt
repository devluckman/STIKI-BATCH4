package com.man.filmku.presentation.main.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.man.filmku.data.database.entity.EntityMovie
import com.man.filmku.domain.model.movie.MovieData
import com.man.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _favoriteData = MutableLiveData<List<EntityMovie>>()
    val favoriteData = _favoriteData
    fun getAllMovieFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMovieFavorite().collect {
                _favoriteData.postValue(it)
            }
        }
    }

    init {
        getAllMovieFavorite()
    }

}