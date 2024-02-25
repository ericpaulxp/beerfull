package com.example.beerfull.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.beerfull.domain.use_cases.BeerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    beerUseCases: BeerUseCases,
) : ViewModel() {

    val beers = beerUseCases.getBeers()
        .cachedIn(viewModelScope)
}