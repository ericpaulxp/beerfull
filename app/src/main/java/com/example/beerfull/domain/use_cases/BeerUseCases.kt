package com.example.beerfull.domain.use_cases

data class BeerUseCases(
    val saveBeer: SaveBeer,
    val deleteBeer: DeleteBeer,
    val getBeerDetails: GetBeerDetails,
    val getFavoriteBeers: GetFavoriteBeers,
    val searchBeer: SearchBeer,
    val getBeers: GetBeers
)
