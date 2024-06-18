package com.man.filmku.presentation.main.bookmark

import android.view.View
import androidx.fragment.app.viewModels
import com.man.filmku.base.BaseFragment
import com.man.filmku.data.database.entity.EntityMovie
import com.man.filmku.data.database.entity.EntityMovie.Companion.toMovieData
import com.man.filmku.databinding.FragmentBookmarkBinding
import com.man.filmku.domain.model.movie.MovieData
import com.man.filmku.presentation.detail.DetailActivity
import com.man.filmku.presentation.main.adapter.AdapterPopular
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(
    FragmentBookmarkBinding::inflate
) {

    val viewModel : BookmarkViewModel by viewModels()
    private val adapterSaved by lazy { AdapterPopular(::openDetail) }

    override fun onViewReady() {
        binding.rvSaved.adapter = adapterSaved
        viewModel.favoriteData.observe(this) {
            adapterSaved.setData(it.toMovieData())
            stateView(it.isNotEmpty())

        }
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = false
                viewModel.getAllMovieFavorite()
            }
        }
    }

    private fun stateView(dataAvailable : Boolean) {
        binding.apply {
            rvSaved.visibility = if (dataAvailable) View.VISIBLE else View.GONE
            lnEmpty.visibility = if (dataAvailable) View.GONE else View.VISIBLE
        }
    }

    private fun openDetail(data : MovieData) {
        DetailActivity.newInstance(context, data.id)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment BookmarkFragment.
         */
        @JvmStatic
        fun newInstance() =
            BookmarkFragment().apply {}
    }
}