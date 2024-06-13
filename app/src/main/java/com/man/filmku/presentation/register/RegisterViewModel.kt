package com.man.filmku.presentation.register

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.man.filmku.data.repository.RepositoryImpl
import com.man.filmku.domain.model.UserData
import com.man.filmku.domain.repository.Repository
import com.man.filmku.domain.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _stateEmailError = MutableLiveData<String?>(null)
    val stateEmailError: LiveData<String?> = _stateEmailError

    private val _statePasswordError = MutableLiveData<String?>(null)
    val statePasswordError: LiveData<String?> = _statePasswordError

    fun doRegister(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        confirmPass: String
    ) {

        var isValid = true

        if (firstName.isBlank()) {
            isValid = false
            // binding.edtFirstName.setError("First Tidak Boleh Kosong")
        }

        if (email.isBlank()) {
            isValid = false
            //binding.edtEmail.setError("Email Tidak Boleh Kosong")
        }

        if (password.isBlank()) {
            isValid = false
            // binding.edtPassword.setError("Password Tidak Boleh Kosong")
        }

        if (confirmPass.isBlank()) {
            isValid = false
            //binding.edtConfirmPassword.setError("Password Tidak Boleh Kosong")
        }

        if (password != confirmPass) {
            isValid = false
            // binding.edtConfirmPassword.setError("Confirm Password Tidak Sama")
        }
        val fullName = "$firstName $lastName"

        if (email.isNotEmpty() && password.isNotEmpty()) {
            createAccount(fullName, email, password)
        }
    }


    private val _stateRegisterSuccess = MutableLiveData<Boolean>()
    val stateRegisterSuccess = _stateRegisterSuccess


    private fun createAccount(name: String, email: String, password: String) {
        repository.doRegister(password = password, name = name, email = email) { data ->

            _stateRegisterSuccess.value = when(data) {
                is Resource.Success -> true
                is Resource.Error -> false
            }

        }
    }

}