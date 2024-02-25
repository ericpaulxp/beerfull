package com.example.beerfull.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.beerfull.domain.model.Beer
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBeer(beer: Beer)

    @Delete
    suspend fun deleteBeer(beer: Beer)

    @Query("SELECT * FROM Beer WHERE id = :id ")
    suspend fun getBeerDetails(id: Int): Beer?

    @Query("SELECT * FROM Beer")
    fun getFavoriteBeers(): Flow<List<Beer>>
}