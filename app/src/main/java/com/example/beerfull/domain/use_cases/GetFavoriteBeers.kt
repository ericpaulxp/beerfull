package com.example.beerfull.domain.use_cases

import com.example.beerfull.domain.model.Beer
import com.example.beerfull.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteBeers @Inject constructor(
    private val beerRepository: BeerRepository
) {
    operator fun invoke(): Flow<List<Beer>> {
        return beerRepository.getFavoriteBeers()
    }
}