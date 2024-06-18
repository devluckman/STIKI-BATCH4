package com.man.filmku.data.remote

import com.man.filmku.data.response.CastResponse
import com.man.filmku.data.response.DetailMovieResponse
import com.man.filmku.data.response.NowPlayingResponse
import com.man.filmku.data.response.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiMovieDB {

    @GET("3/movie/now_playing")
    suspend fun getNowPlaying(
        @Header("Authorization") token: String
    ): NowPlayingResponse

    @GET("3/movie/popular")
    suspend fun getPopular(
        @Header("Authorization") token: String
    ): PopularResponse

    @GET("3/movie/{id}")
    suspend fun getDetailMovie(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DetailMovieResponse

    @GET("3/movie/{id}/credits")
    suspend fun getCastMovie(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): CastResponse


}