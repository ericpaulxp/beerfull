package com.example.beerfull.presentation.beer_navigation

sealed class Route(
    val route: String
) {
    data object HomeScreen : Route(route = "homeScreen")
    data object SearchScreen : Route(route = "searchScreen")
    data object FavoritesScreen : Route(route = "favoritesScreen")
    data object DetailsScreen : Route(route = "detailsScreen")
}