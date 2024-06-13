package com.man.filmku.presentation.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.man.filmku.R
import com.man.filmku.databinding.FragmentHomeBinding
import com.man.filmku.presentation.landing.LandingActivity
import com.man.filmku.presentation.main.adapter.AdapterNowShowing
import com.man.filmku.presentation.main.adapter.AdapterPopular
import com.man.filmku.domain.model.movie.MovieData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding : FragmentHomeBinding
    val binding get() = _binding

    val viewModel : HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var adapterNowShowing : AdapterNowShowing? = null
    private var adapterPopular : AdapterPopular? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firebaseAuth = FirebaseAuth.getInstance()
        binding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            goToSplash()
        }

        adapterNowShowing = AdapterNowShowing()
        binding.rvNowShowing.adapter = adapterNowShowing

        adapterPopular = AdapterPopular()
        binding.rvPopular.adapter = adapterPopular

        viewModel.nowShowingMovies.observe(viewLifecycleOwner) {
            adapterNowShowing?.setData(it)
        }

        viewModel.nowPopularMovies.observe(viewLifecycleOwner) {
            adapterPopular?.setData(it)
        }

        viewModel.getNowShowing()
        viewModel.getPopular()
    }


    private fun goToSplash() {
        val intent = Intent(context, LandingActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() = HomeFragment().apply {

            }
    }
}