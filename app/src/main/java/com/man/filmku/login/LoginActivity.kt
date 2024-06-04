package com.man.filmku.login

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
import com.man.filmku.databinding.ActivityLoginBinding
import com.man.filmku.main.MainActivity
import com.man.filmku.register.RegisterActivity
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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