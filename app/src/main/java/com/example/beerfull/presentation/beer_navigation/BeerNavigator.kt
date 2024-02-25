package com.example.beerfull.presentation.beer_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.beerfull.R
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.presentation.home.HomeScreen
import com.example.beerfull.presentation.home.HomeViewModel
import com.example.beerfull.presentation.details.DetailsScreen
import com.example.beerfull.presentation.details.DetailsViewModel
import com.example.beerfull.presentation.favorites.FavoriteBeerViewModel
import com.example.beerfull.presentation.favorites.FavoritesScreen
import com.example.beerfull.presentation.search.SearchScreen
import com.example.beerfull.presentation.search.SearchViewModel

@Composable
fun BeerNavigator() {

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.ic_home,
                text = "Home"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_search,
                text = "Search"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_bookmark,
                text = "Favorites"
            )
        )
    }

    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.FavoritesScreen.route -> 2
            else -> 0
        }
    }
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.FavoritesScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                BeerBottomNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(navController, Route.HomeScreen.route)
                            1 -> navigateToTab(navController, Route.SearchScreen.route)
                            2 -> navigateToTab(navController, Route.FavoritesScreen.route)
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(
                    beers = viewModel.beers.collectAsLazyPagingItems(),
                    navigateToSearchScreen = {
                        navigateToTab(navController, Route.SearchScreen.route)
                    },
                    navigateToDetailsScreen = { beer ->
                        navigateToDetails(navController, beer = beer)
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(
                    state = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigateToDetailScreen = { beer ->
                        navigateToDetails(navController, beer)
                    }
                )
            }
            composable(route = Route.FavoritesScreen.route) {
                val viewModel: FavoriteBeerViewModel = hiltViewModel()
                FavoritesScreen(
                    state = viewModel.state.collectAsState().value,
                    navigateToDetailsScreen = { beer ->
                        navigateToDetails(navController, beer)
                    }
                )
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Beer?>("beer")
                    ?.let { beer ->
                        DetailsScreen(
                            beer = beer,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            sideEffect = viewModel.sideEffect

                        )
                    }
            }
        }
    }
}

private fun navigateToTab(
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(
    navController: NavController,
    beer: Beer
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("beer", beer)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}