package com.man.filmku.domain.repository

import com.man.filmku.data.database.entity.EntityMovie
import com.man.filmku.domain.model.movie.CastMovieData
import com.man.filmku.domain.model.movie.MovieData
import com.man.filmku.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    // Splash
    val isLogin: Boolean

    // Page Login
    fun doLogin(email: String, password: String, callback: (Resource<Boolean>) -> Unit)

    fun logout()

    // Page Register
    fun doRegister(password : String, email: String, name: String, callback: (Resource<Boolean>) -> Unit)
    fun getNowPlayingMovie() : Flow<List<MovieData>>
    fun getPopularMovie() : Flow<List<MovieData>>

    fun getDetailMovie(idMovie : Int) : Flow<EntityMovie>
    fun getCast(idMovie : Int) : Flow<List<CastMovieData>>

    fun getAllMovieFavorite() : Flow<List<EntityMovie>>

    fun updateOrDeleteMovieInFavorite(data : EntityMovie) : Flow<Boolean>
}