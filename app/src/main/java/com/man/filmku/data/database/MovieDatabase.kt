package com.man.filmku.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.man.filmku.data.database.dao.MovieDao
import com.man.filmku.data.database.entity.EntityMovie

@Database(entities = [EntityMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

}