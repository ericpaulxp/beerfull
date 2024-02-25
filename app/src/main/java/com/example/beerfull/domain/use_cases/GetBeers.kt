package com.example.beerfull.domain.use_cases

import androidx.paging.PagingData
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeers @Inject constructor(
    private val beerRepository: BeerRepository
) {
    operator fun invoke(): Flow<PagingData<Beer>> {
        return beerRepository.getAllBeers()
    }
}