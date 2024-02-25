package com.example.beerfull.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    data object SearchBeers : SearchEvent()
}