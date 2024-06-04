package com.man.filmku.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.man.filmku.R
import com.man.filmku.databinding.ActivityRegisterBinding
import com.man.filmku.login.LoginActivity
import com.man.filmku.model.UserData

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private val viewModel : RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        viewModel.stateRegisterSuccess.observe(this) {
            if (it) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        binding.apply {

            btnRegister.setOnClickListener {
                viewModel.doRegister(
                    email = edtEmail.editText.text.toString(),
                    password = edtPassword.editText.text.toString(),
                    firstName = edtFirstName.editText.text.toString(),
                    lastName = edtLastName.editText.text.toString(),
                    confirmPass = edtConfirmPassword.editText.text.toString()
                )
            }

        }

    }
}