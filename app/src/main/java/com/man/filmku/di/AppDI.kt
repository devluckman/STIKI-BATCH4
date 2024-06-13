package com.man.filmku.di

import com.google.firebase.auth.FirebaseAuth
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


}