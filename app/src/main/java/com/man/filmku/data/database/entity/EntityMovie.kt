package com.man.filmku.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.man.filmku.domain.model.movie.MovieData

@Entity(tableName = "movie_table")
data class EntityMovie (
    @PrimaryKey
    val id : Int,
    val image : String,
    val backdrop : String = "",
    val title : String,
    val rating : String,
    val duration : String,
    val genre : String,
    val description : String,
    val isBookmark : Boolean
) {

    companion object {
        val dummy = EntityMovie(
            id = 1,
            image = "",
            title = "Spiderman: No Way Home",
            rating = "8.1/10",
            duration = "1h 47m",
            genre = "Action,Adventure, Fantasy",
            description = "With Spider-Man's identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear, forcing Peter to discover what it truly means to be Spider-Man.",
            isBookmark = true
        )

        fun List<EntityMovie>.toMovieData() : List<MovieData> {
            return map {
                MovieData(
                    id = it.id,
                    image = it.image,
                    rating = it.rating,
                    title = it.title
                )
            }
        }

    }
}