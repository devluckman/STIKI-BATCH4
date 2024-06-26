package com.man.filmku.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.man.filmku.R

abstract class BaseActivity<VB : ViewBinding>(
    private val inflate : (LayoutInflater) -> VB
) : AppCompatActivity() {

    val binding by lazy {
        inflate.invoke(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        onViewReady()
    }

    abstract fun onViewReady()
}