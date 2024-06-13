package com.man.filmku.data.repository

import com.man.filmku.domain.repository.Repository

class RepositoryImpl : Repository {
    
    override val isLogin: Boolean
        get() = true

    override fun doLogin(email: String, password: String) {
        // Login to firebase
    }

    override fun doRegister(email: String, name: String) {
        // Register to firebase
    }

    override fun getMovieNowShowing() {
        // Get Data from API
    }

    override fun getMoviePopular() {
        // Get Data from API
    }

}