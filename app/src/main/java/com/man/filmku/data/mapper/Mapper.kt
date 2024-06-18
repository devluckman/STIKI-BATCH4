package com.man.filmku.data.mapper

import com.man.filmku.data.database.entity.EntityMovie
import com.man.filmku.data.response.CastResponse
import com.man.filmku.data.response.DetailMovieResponse
import com.man.filmku.data.response.NowPlayingResponse
import com.man.filmku.data.response.PopularResponse
import com.man.filmku.domain.model.movie.CastMovieData
import com.man.filmku.domain.model.movie.MovieData

object Mapper {

    private val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"

    fun nowPlayingToDomain(data: NowPlayingResponse): List<MovieData> {
        return data.results?.map {
            MovieData(
                title = it.originalTitle.orEmpty(),
                rating = String.format("%.1f/10", it.voteAverage),
                image = BASE_URL_IMAGE + it.posterPath,
                id = it.id
            )
        } ?: emptyList()
    }

    fun popularToDomain(data: PopularResponse): List<MovieData> {
        return data.results?.map {
            MovieData(
                title = it.originalTitle.orEmpty(),
                rating = String.format("%.1f/10", it.voteAverage),
                image = BASE_URL_IMAGE + it.posterPath,
                id = it.id
            )
        } ?: emptyList()
    }

    fun detailToDomain(data: DetailMovieResponse): EntityMovie {

        return EntityMovie(
            id = data.id,
            title = data.originalTitle.orEmpty(),
            description = data.overview.orEmpty(),
            genre = data.genres?.map { it.name.orEmpty() }?.joinToString(",") ?: "",
            rating = String.format("%.1f/10", data.voteAverage),
            image = BASE_URL_IMAGE + data.posterPath,
            backdrop = BASE_URL_IMAGE + data.backdropPath,
            duration = minutesToHourMinute(data.runtime ?: 0),
            isBookmark = false
        )
    }

    private fun minutesToHourMinute(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60 // Sisa Bagi 113 % 60 = 53 or 200 % 60 = 0

        val hoursStr = if (hours > 0) "$hours h " else ""
        val minutesStr = if (remainingMinutes > 0) "$remainingMinutes m" else ""

        return "$hoursStr$minutesStr"
    }

    fun castToDomain(data: CastResponse): List<CastMovieData> {
        return data.cast?.map {
            CastMovieData(
                name = it.name.orEmpty(),
                picture = BASE_URL_IMAGE + it.profilePath
            )
        } ?: emptyList()
    }

}