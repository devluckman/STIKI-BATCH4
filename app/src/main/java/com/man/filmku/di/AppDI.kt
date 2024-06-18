package com.man.filmku.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.man.filmku.data.database.MovieDatabase
import com.man.filmku.data.remote.ApiMovieDB
import com.man.filmku.data.repository.RepositoryImpl
import com.man.filmku.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppDI {

    @Provides
    fun providesFirebase(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MovieDatabase::class.java,
            name = "movie_db"
        ).build()
    }

    @Provides
    fun provideApi(): ApiMovieDB {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiMovieDB::class.java)
    }


    @Provides
    fun providesRepository(
        firebaseAuth: FirebaseAuth,
        apiMovieDB: ApiMovieDB,
        movieDatabase: MovieDatabase
    ): Repository {
        return RepositoryImpl(firebaseAuth, apiMovieDB, movieDatabase)
    }


}