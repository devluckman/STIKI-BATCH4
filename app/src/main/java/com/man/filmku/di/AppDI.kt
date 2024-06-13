package com.man.filmku.di

import com.google.firebase.auth.FirebaseAuth
import com.man.filmku.data.repository.RepositoryImpl
import com.man.filmku.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDI {

    @Provides
    fun providesFirebase() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


    @Provides
    fun providesRepository(
        firebaseAuth: FirebaseAuth
    ) : Repository {
        return RepositoryImpl(firebaseAuth)
    }

}