package com.example.beerfull.domain.repository

import androidx.paging.PagingData
import com.example.beerfull.domain.model.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    fun getAllBeers(): Flow<PagingData<Beer>>

    fun searchBeers(searchQuery: String): Flow<PagingData<Beer>>

    suspend fun getBeerDetails(id: Int): Beer?

    suspend fun saveBeer(beer: Beer)

    suspend fun deleteBeer(beer: Beer)

    fun getFavoriteBeers(): Flow<List<Beer>>
}