package com.example.beerfull.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.beerfull.domain.use_cases.BeerUseCases

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val beerUseCases: BeerUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            SearchEvent.SearchBeers -> {
                searchBeers()
            }

            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }
        }
    }

    private fun searchBeers() {
        val beers = beerUseCases.searchBeer(
            state.value.searchQuery
        )
            .cachedIn(viewModelScope)
        _state.value = state.value.copy(beers = beers)
    }
}

