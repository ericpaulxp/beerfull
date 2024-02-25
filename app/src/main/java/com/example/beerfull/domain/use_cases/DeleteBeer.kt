package com.example.beerfull.domain.use_cases

import com.example.beerfull.domain.model.Beer
import com.example.beerfull.domain.repository.BeerRepository
import javax.inject.Inject

class DeleteBeer @Inject constructor(
    private val beerRepository: BeerRepository
) {
    suspend operator fun invoke(beer: Beer) {
        beerRepository.deleteBeer(beer)
    }
}