package com.example.beerfull.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.presentation.common.BeerList
import com.example.beerfull.utils.Dimens.paddingSmall


@Composable
fun HomeScreen(
    beers: LazyPagingItems<Beer>,
    navigateToDetailsScreen: (Beer) -> Unit,
    navigateToSearchScreen: () -> Unit,
) {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            HomeTopBar(
                onSearchClicked = navigateToSearchScreen
            )
        }
    ) {
        val paddingTop = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingTop, start = 12.dp, end = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "All Beers",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = paddingSmall),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(paddingSmall))
            BeerList(beer = beers, onCLick = {
                navigateToDetailsScreen(it)
            })
        }
    }
}
