package com.man.filmku.domain.model.movie

data class MovieData (
    val image : String,
    val title : String,
    val rating : String
) {


    companion object {
        val dummy = listOf(
            MovieData(
                image = "",
                title = "Spiderman: No Way Home",
                rating = "9.1/10"
            ),
            MovieData(
                image = "",
                title = "Eternals",
                rating = "9.2/10"
            ),
            MovieData(
                image = "",
                title = "Spiderman: No Way Home",
                rating = "9.1/10"
            ),
            MovieData(
                image = "",
                title = "Eternals",
                rating = "9.2/10"
            ),
            MovieData(
                image = "",
                title = "Spiderman: No Way Home",
                rating = "9.1/10"
            ),
            MovieData(
                image = "",
                title = "Eternals",
                rating = "9.2/10"
            )
        )
    }
}