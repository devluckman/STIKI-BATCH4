package com.man.filmku.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.man.filmku.R
import com.man.filmku.databinding.ActivityRegisterBinding
import com.man.filmku.model.UserData

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

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


        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener {
            doRegister()
        }
    }

    private fun doRegister() {
        val firstName = binding.edtFirstName.editText.text.toString()
        val lastName = binding.edtLastName.editText.text.toString()
        val fullName = "$firstName $lastName"
        val email = binding.edtEmail.editText.text.toString()
        val password = binding.edtPassword.editText.text.toString()
        val confirmPass = binding.edtConfirmPassword.editText.text.toString()

        var isValid = true

        if (firstName.isBlank()) {
            isValid = false
            binding.edtFirstName.setError("First Tidak Boleh Kosong")
        }

        if (email.isBlank()) {
            isValid = false
            binding.edtEmail.setError("Email Tidak Boleh Kosong")
        }

        if (password.isBlank()) {
            isValid = false
            binding.edtPassword.setError("Password Tidak Boleh Kosong")
        }

        if (confirmPass.isBlank()) {
            isValid = false
            binding.edtConfirmPassword.setError("Password Tidak Boleh Kosong")
        }

        if (password != confirmPass) {
            isValid = false
            binding.edtConfirmPassword.setError("Confirm Password Tidak Sama")
        }

        if (isValid) {
            createAccount(fullName, email, password)
        }
    }


    private fun createAccount(name : String, email : String, password : String) {
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

                        Toast.makeText(this, "Register Succes", Toast.LENGTH_LONG).show()
                        finish()
                    }

            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }
}