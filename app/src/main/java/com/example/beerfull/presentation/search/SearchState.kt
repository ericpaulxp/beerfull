package com.example.beerfull.presentation.search

import androidx.paging.PagingData
import com.example.beerfull.domain.model.Beer
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val beers: Flow<PagingData<Beer>>? = null
)
