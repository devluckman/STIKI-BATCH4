package com.man.filmku.domain.repository

import com.man.filmku.domain.model.movie.MovieData
import com.man.filmku.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    // Splash
    val isLogin: Boolean

    // Page Login
    fun doLogin(email: String, password: String, callback: (Resource<Boolean>) -> Unit)

    // Page Register
    fun doRegister(password : String, email: String, name: String, callback: (Resource<Boolean>) -> Unit)

    fun getMovieNowShowing() : List<MovieData>

    fun getMoviePopular() : List<MovieData>

}