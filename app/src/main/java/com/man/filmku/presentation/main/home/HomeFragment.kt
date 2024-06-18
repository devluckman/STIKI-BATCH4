package com.man.filmku.presentation.main.home

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.man.filmku.base.BaseFragment
import com.man.filmku.databinding.FragmentHomeBinding
import com.man.filmku.domain.model.movie.MovieData
import com.man.filmku.presentation.landing.LandingActivity
import com.man.filmku.presentation.main.adapter.AdapterNowShowing
import com.man.filmku.presentation.main.adapter.AdapterPopular
import com.man.filmku.presentation.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import java.lang.RuntimeException

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapterNowShowing by lazy { AdapterNowShowing(::openDetail) }
    private val adapterPopular by lazy { AdapterPopular(::openDetail) }

    override fun onViewReady() {
        binding.rvNowShowing.adapter = adapterNowShowing
        binding.rvPopular.adapter = adapterPopular
        binding.btnLogout.setOnClickListener {
            goToLogout()
            // throw RuntimeException("Test Crash")
        }
        viewModel.nowPlayingData.observe(this) {
            adapterNowShowing.setData(it.orEmpty())
        }

        viewModel.popularData.observe(this) {
            adapterPopular.setData(it.orEmpty())
        }
    }

    private fun goToLogout() {
        activity?.apply {
            viewModel.logout()
            startActivity(Intent(this, LandingActivity::class.java))
            finish()
        }
    }

    private fun openDetail(data : MovieData) {
         DetailActivity.newInstance(context, data.id)
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