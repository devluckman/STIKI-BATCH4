package com.man.filmku.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.man.filmku.R
import com.man.filmku.base.BaseActivity
import com.man.filmku.data.database.entity.EntityMovie
import com.man.filmku.databinding.ActivityDetailBinding
import com.man.filmku.presentation.adapter.CastAdapter
import com.man.filmku.presentation.adapter.GenreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(
    ActivityDetailBinding::inflate
) {

    private val viewModel: DetailViewModel by viewModels()
    private val genreAdapter by lazy { GenreAdapter() }
    private val castAdapter by lazy { CastAdapter() }
    override fun onViewReady() {
        binding.rvCastMovie.adapter = castAdapter
        binding.rvGenres.adapter = genreAdapter
        viewModel.detailDataMovie.observe(this) { data ->
            binding.apply {
                tvMovieName.text = data.title
                tvImbd.text = data.rating
                tvDuration.text = data.duration
                tvDescription.text = data.description
                Glide.with(ivPoster)
                    .load(data.backdrop)
                    .into(ivPoster)
                val genres = data.genre.split(",")
                genreAdapter.submit(genres)

                val iconFavorite = if (data.isBookmark)
                    R.drawable.ic_bookmark_on
                else R.drawable.ic_bookmark_off
                btnBookmark.setImageResource(iconFavorite)
                btnBookmark.setOnClickListener {
                    viewModel.updateDataFavorite(data)
                }
            }
        }

        viewModel.castMovieData.observe(this) {
            castAdapter.submit(it)
        }

        viewModel.favoriteState.observe(this) { data ->
            val iconFavorite = if (data)
                R.drawable.ic_bookmark_on
            else R.drawable.ic_bookmark_off
            binding.btnBookmark.setImageResource(iconFavorite)
        }

        val id = intent.getIntExtra(KEY_ID_MOVIE, 0)
        viewModel.getDetailMovie(id)
        viewModel.getCastMovie(id)
    }

    companion object {
        const val KEY_ID_MOVIE = "KEY_ID_MOVIE"
        fun newInstance(context: Context?, id: Int) {
            context?.let {
                val intent = Intent(it, DetailActivity::class.java)
                intent.putExtra(KEY_ID_MOVIE, id)
                it.startActivity(intent)
            }
        }
    }
}