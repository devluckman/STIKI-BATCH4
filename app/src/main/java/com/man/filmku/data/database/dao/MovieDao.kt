package com.man.filmku.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.man.filmku.data.database.entity.EntityMovie


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAllMovieBookmark() : List<EntityMovie>

    @Insert
    fun addMovieToBookmark(data : EntityMovie)

    @Delete
    fun deleteMovie(data: EntityMovie)

    @Query("SELECT * FROM movie_table WHERE id=:id")
    suspend fun findMovie(id: Int): EntityMovie?

}