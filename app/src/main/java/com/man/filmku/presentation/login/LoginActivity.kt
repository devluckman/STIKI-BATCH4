package com.man.filmku.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth
import com.man.filmku.R
import com.man.filmku.base.BaseActivity
import com.man.filmku.databinding.ActivityLoginBinding
import com.man.filmku.presentation.main.MainActivity
import com.man.filmku.presentation.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(
    ActivityLoginBinding::inflate
) {

    val viewModel: LoginViewModel by viewModels()

    override fun onViewReady() {
        viewModel.stateEmailError.observe(this) { messageError ->
            if (messageError != null) {
                binding.edtEmail.setError(messageError)
            }
        }

        viewModel.statePasswordError.observe(this) { messageError ->
            if (messageError != null) {
                binding.edtPassword.setError(messageError)
            }
        }

        viewModel.stateLoginSuccess.observe(this) { success ->
            if (success) {
                goToHome()
            } else {
                Toast.makeText(this, "Login Gagal", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnLogin.setOnClickListener {

            viewModel.doLogin(
                email = binding.edtEmail.editText.text.toString(),
                password = binding.edtPassword.editText.text.toString()
            )

        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLoginGoogle.setOnClickListener {
            viewModel.setData(
                data = binding.edtEmail.editText.text.toString()
            )
        }

        viewModel.sampleData.observe(this) {
            binding.tvSampleData.text = it
        }
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}