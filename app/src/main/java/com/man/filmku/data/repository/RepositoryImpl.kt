package com.man.filmku.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.man.filmku.domain.model.UserData
import com.man.filmku.domain.model.movie.MovieData
import com.man.filmku.domain.repository.Repository
import com.man.filmku.domain.resource.Resource

class RepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : Repository {
    
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

    override fun getMovieNowShowing(): List<MovieData> {
        // Get Data from API
        return MovieData.dummy
    }

    override fun getMoviePopular(): List<MovieData> {
        // Get Data from API
        return MovieData.dummy
    }

}