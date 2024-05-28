package com.man.filmku.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.man.filmku.R
import com.man.filmku.databinding.ActivityLoginBinding
import com.man.filmku.home.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
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

        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener {
            doLogin()
        }

        binding.btnLoginGoogle.setOnClickListener {
            doRegister()
        }
    }

    private fun doLogin() {
        val email = binding.edtEmail.editText.text.toString()
        val password = binding.edtPassword.editText.text.toString()

        if (email.isBlank()) {
            binding.edtEmail.setError("Email Tidak Boleh Kosong")
        }

        if (password.isBlank()) {
            binding.edtPassword.setError("Password Tidak Boleh Kosong")
        }

        if (email.isNotEmpty() && password.isNotEmpty()) {
            loginToFirebase(email, password)
        }
    }

    private fun loginToFirebase(email : String, password : String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Action Success
                goToHome()
                Toast.makeText(this, "Success Login", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                // Action Failed
                Toast.makeText(this, "Failed Login", Toast.LENGTH_LONG).show()
            }
    }

    private fun doRegister() {
        firebaseAuth.createUserWithEmailAndPassword("lukmanul.hakim@gmail.com", "password")
            .addOnSuccessListener {
                Toast.makeText(this, "Register Success", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}