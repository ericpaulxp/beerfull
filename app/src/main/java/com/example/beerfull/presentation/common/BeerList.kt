package com.example.beerfull.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.beerfull.domain.model.Beer

@Composable
fun BeerList(
    beer: LazyPagingItems<Beer>,
    modifier: Modifier = Modifier,
    onCLick: (Beer) -> Unit

) {
    val handlePagingResult = handlePagingResult(beer = beer)
    if (handlePagingResult) {

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(count = beer.itemCount) {
                beer[it]?.let { beer ->
                    BeerCard(beer = beer, onClick = { onCLick(beer) })
                }
                Divider()
            }
        }
    }
}

@Composable
fun BeerFavList(
    beer: List<Beer>,
    modifier: Modifier = Modifier,
    onCLick: (Beer) -> Unit,

) {
    if (beer.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(count = beer.size, key = { beer[it] })
        {

            beer[it].let { beer ->
                BeerCard(beer = beer, onClick = { onCLick(beer) })
            }
            Divider()
        }
    }
}


@Composable
fun handlePagingResult(
    beer: LazyPagingItems<Beer>
): Boolean {
    val loadState = beer.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error

        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error)
            false
        }

        beer.itemCount == 0 -> {
            EmptyScreenSearch()
            false
        }


        else -> true
    }
}

@Composable
private fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        repeat(5) {
            BeerCardShimmerEffect()
        }
    }
}


