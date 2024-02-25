package com.example.beerfull.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerfull.domain.use_cases.BeerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteBeerViewModel @Inject constructor(
    private val beerUseCases: BeerUseCases,
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteBeerState())
    val state = _state.asStateFlow()


    init {
        getFavBeers()
    }

    private fun getFavBeers() {
        beerUseCases.getFavoriteBeers().onEach {
            _state.value = state.value.copy(beers = (it.reversed()))
        }.launchIn(viewModelScope)
    }
}



