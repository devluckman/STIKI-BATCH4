package com.man.filmku.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _stateEmailError = MutableLiveData<String?>(null)
    val stateEmailError : LiveData<String?> = _stateEmailError


    private val _statePasswordError = MutableLiveData<String?>(null)
    val statePasswordError : LiveData<String?> = _statePasswordError

    fun doLogin(email: String, password: String) {

        if (email.isBlank()) {
            _stateEmailError.value = "Email Tidak Boleh Kosong"
        }

        if (password.isBlank()) {
            _statePasswordError.value = "Password Tidak Boleh Kosong"
        }

        if (email.isNotEmpty() && password.isNotEmpty()) {
            loginToFirebase(email, password)
        }
    }

    private val _sampleData = MutableLiveData<String?>(null)
    val sampleData : LiveData<String?> = _sampleData

    fun setData(data : String) {
        Log.d("TEST", "CHECK DATA $data")
        _sampleData.value = data
    }

    private val _stateLoginSuccess = MutableLiveData<Boolean>()
    val stateLoginSuccess = _stateLoginSuccess

    private fun loginToFirebase(email : String, password : String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Action Success
                _stateLoginSuccess.value = true
            }
            .addOnFailureListener {
                // Action Failed
                _stateLoginSuccess.value = false
            }
    }

}