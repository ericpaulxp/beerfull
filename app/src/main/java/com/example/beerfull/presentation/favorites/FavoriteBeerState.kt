package com.example.beerfull.presentation.favorites

import com.example.beerfull.domain.model.Beer


data class FavoriteBeerState(
    val beers: List<Beer> = emptyList()
)