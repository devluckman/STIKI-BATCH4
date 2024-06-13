package com.man.filmku.domain.repository

interface Repository {

    // Splash
    val isLogin : Boolean

    // Page Login
    fun doLogin(email : String, password : String)

    // Page Register
    fun doRegister(email : String, name : String)

    fun getMovieNowShowing()

    fun getMoviePopular()

}