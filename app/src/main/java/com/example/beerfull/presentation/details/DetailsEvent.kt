package com.example.beerfull.presentation.details

import com.example.beerfull.domain.model.Beer


sealed class DetailsEvent {
    data class UpsertDeleteFavoriteBeer(val beer: Beer) : DetailsEvent()

    data object RemoveSideEffect : DetailsEvent()
}