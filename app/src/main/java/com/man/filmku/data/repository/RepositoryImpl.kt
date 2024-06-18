package com.man.filmku.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.man.filmku.BuildConfig
import com.man.filmku.data.database.MovieDatabase
import com.man.filmku.data.database.entity.EntityMovie
import com.man.filmku.data.mapper.Mapper
import com.man.filmku.data.remote.ApiMovieDB
import com.man.filmku.domain.model.UserData
import com.man.filmku.domain.model.movie.CastMovieData
import com.man.filmku.domain.model.movie.MovieData
import com.man.filmku.domain.repository.Repository
import com.man.filmku.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val apiMovieDB: ApiMovieDB,
    private val database: MovieDatabase
) : Repository {

    private val theMovieDBKey = BuildConfig.AUTH_TOKEN_API
    override val isLogin: Boolean
        get() = firebaseAuth.currentUser != null

    override fun doLogin(email: String, password: String, callback: (Resource<Boolean>) -> Unit) {
        // Login to firebase
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Action Success
                callback.invoke(Resource.Success(true))
            }
            .addOnFailureListener {
                // Action Failed
                it.printStackTrace()
                callback.invoke(Resource.Error(message = "Gagal Login"))
            }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun doRegister(
        password: String,
        email: String,
        name: String,
        callback: (Resource<Boolean>) -> Unit
    ) {
        // Register to firebase
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Save to FireStore
                val data = UserData(
                    name = name,
                    email = email
                )
                val uid = it.user?.uid ?: ""
                Firebase.firestore.collection("users")
                    .document(uid)
                    .set(data)
                    .addOnSuccessListener {
                       callback.invoke(Resource.Success(true))
                    }

            }
            .addOnFailureListener {
                it.printStackTrace()
                callback.invoke(Resource.Error(message = "Gagal Register"))
            }
    }

    override fun getNowPlayingMovie(): Flow<List<MovieData>> = flow {
        try {
            val data = apiMovieDB.getNowPlaying(token = "Bearer $theMovieDBKey")
            emit(Mapper.nowPlayingToDomain(data))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getPopularMovie(): Flow<List<MovieData>> = flow {
        try {
            val data = apiMovieDB.getPopular(token = "Bearer $theMovieDBKey")
            emit(Mapper.popularToDomain(data))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getDetailMovie(idMovie: Int): Flow<EntityMovie> = flow {
        try {
            val movieData = database.movieDao().findMovie(idMovie)
            if (movieData != null) {
                emit(movieData)
            } else {
                val data = apiMovieDB.getDetailMovie(
                    token = "Bearer $theMovieDBKey",
                    id = idMovie
                )
                emit(Mapper.detailToDomain(data))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getCast(idMovie: Int): Flow<List<CastMovieData>> = flow {
        try {
            val data = apiMovieDB.getCastMovie(
                token = "Bearer $theMovieDBKey",
                id = idMovie
            )
            emit(Mapper.castToDomain(data))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getAllMovieFavorite(): Flow<List<EntityMovie>> = flow {
        val data = database.movieDao().getAllMovieBookmark()
        emit(data)
    }

    override fun updateOrDeleteMovieInFavorite(data: EntityMovie): Flow<Boolean> = flow  {
        val movieData = database.movieDao().findMovie(data.id)
        val dataReadyInDatabase = movieData != null
        if (dataReadyInDatabase) {
            database.movieDao().deleteMovie(data)
            emit(false)
        } else {
            val newData = data.copy(isBookmark = true) // Flag
            database.movieDao().addMovieToBookmark(newData)
            emit(true)
        }
    }


}