package com.example.beerfull.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beerfull.domain.model.Beer

@Database(
    entities = [Beer::class],
    version = 1
)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun favoriteBeerDao(): FavoriteBeerDao
}