package com.example.beerfull.domain.use_cases

import com.example.beerfull.domain.model.Beer
import com.example.beerfull.domain.repository.BeerRepository
import javax.inject.Inject

class GetBeerDetails @Inject constructor(
    private val beerRepository: BeerRepository
) {
    suspend operator fun invoke(id: Int): Beer? {
        return beerRepository.getBeerDetails(id)
    }
}