package com.example.beerfull.presentation.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.presentation.common.BeerFavList
import com.example.beerfull.utils.Dimens.paddingMedium
import com.example.beerfull.utils.Dimens.paddingSmall


@Composable
fun FavoritesScreen(
    state: FavoriteBeerState,
    navigateToDetailsScreen: (Beer) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(paddingMedium)
    ) {
        Text(text = "Favorites", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(paddingSmall))

        BeerFavList(
            beer = state.beers,
            onCLick = { navigateToDetailsScreen(it) },

        )

    }
}