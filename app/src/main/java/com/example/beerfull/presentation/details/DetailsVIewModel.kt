package com.example.beerfull.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.domain.use_cases.BeerUseCases
import com.example.beerfull.utils.UIComponent

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val beerUseCases: BeerUseCases,

    ) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {

            DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }

            is DetailsEvent.UpsertDeleteFavoriteBeer -> {
                viewModelScope.launch() {

                    val beer1 = beerUseCases.getBeerDetails(id = event.beer.id)
                    if (beer1 == null) {
                        upsertBeer(event.beer)
                    } else {
                        deleteBeer(event.beer)
                    }
                }
            }
        }
    }

    private suspend fun upsertBeer(beer: Beer) {
        beerUseCases.saveBeer(beer)
        sideEffect = UIComponent.Toast("Added To Favorites")
    }

    private suspend fun deleteBeer(beer: Beer) {
        beerUseCases.deleteBeer(beer)
        sideEffect = UIComponent.Toast("Removed From Favorites")
    }

}