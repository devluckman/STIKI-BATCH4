package com.man.filmku.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.man.filmku.R
import com.man.filmku.databinding.ActivityMainBinding
import com.man.filmku.landing.LandingActivity
import com.man.filmku.main.bookmark.BookmarkFragment
import com.man.filmku.main.home.HomeFragment
import com.man.filmku.widget.CustomBottomNavigation

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomNavigation.listerPositionNavigation(

            callback = object : CustomBottomNavigation.CallbackNav {
                override fun onPagePosition(menu: CustomBottomNavigation.MenuNavigation) {

                    val fragment = when (menu) {
                        CustomBottomNavigation.MenuNavigation.HOME -> HomeFragment.newInstance()
                        CustomBottomNavigation.MenuNavigation.BOOKMARK -> BookmarkFragment.newInstance()
                    }

                    inflateFragment(fragment)
                }
            }
        )

        inflateFragment(HomeFragment.newInstance())
    }

    private fun inflateFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}