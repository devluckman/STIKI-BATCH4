package com.man.filmku.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.man.filmku.domain.repository.Repository
import com.man.filmku.domain.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _stateEmailError = MutableLiveData<String?>(null)
    val stateEmailError: LiveData<String?> = _stateEmailError


    private val _statePasswordError = MutableLiveData<String?>(null)
    val statePasswordError: LiveData<String?> = _statePasswordError

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
    val sampleData: LiveData<String?> = _sampleData

    fun setData(data: String) {
        _sampleData.value = data
    }

    private val _stateLoginSuccess = MutableLiveData<Boolean>()
    val stateLoginSuccess = _stateLoginSuccess

    private fun loginToFirebase(email: String, password: String) {
        repository.doLogin(email, password) { data ->

            _stateLoginSuccess.value = when (data) {
                is Resource.Success -> true
                is Resource.Error -> false
            }

        }
    }

}