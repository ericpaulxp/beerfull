package com.example.beerfull.domain.use_cases

import androidx.paging.PagingData
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchBeer @Inject constructor(
    private val beerRepository: BeerRepository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<Beer>> {
        return beerRepository.searchBeers(searchQuery)
    }
}