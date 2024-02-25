package com.example.beerfull.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.beerfull.data.local.FavoriteBeerDao
import com.example.beerfull.data.paging.BeerPagingSource
import com.example.beerfull.data.paging.SearchPagingSource
import com.example.beerfull.data.remote.BeerApi
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.domain.repository.BeerRepository
import com.example.beerfull.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class BeerRepositoryImpl(
    private val beerApi: BeerApi,
    private val favoriteBeerDao: FavoriteBeerDao
) : BeerRepository {
    override fun getAllBeers(): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                BeerPagingSource(beerApi)
            }
        ).flow
    }

    override fun searchBeers(searchQuery: String): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(beerApi, searchQuery)
            }
        ).flow
    }

    override suspend fun getBeerDetails(id: Int): Beer? {
        return favoriteBeerDao.getBeerDetails(id)
    }

    override suspend fun saveBeer(beer: Beer) {
        favoriteBeerDao.saveBeer(beer)
    }

    override suspend fun deleteBeer(beer: Beer) {
        favoriteBeerDao.deleteBeer(beer)
    }

    override fun getFavoriteBeers(): Flow<List<Beer>> {
        return favoriteBeerDao.getFavoriteBeers()
    }
}