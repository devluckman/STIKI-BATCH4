package com.man.filmku.presentation.landing

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    val firebaseUser: FirebaseUser? get() = firebaseAuth.currentUser

}