package com.example.beerfull.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.presentation.common.BeerList
import com.example.beerfull.presentation.common.SearchBar
import com.example.beerfull.utils.Dimens.paddingMedium

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetailScreen: (Beer) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = paddingMedium,
                start = paddingMedium,
                end = paddingMedium
            )
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchBeers) }
        )
        Spacer(modifier = Modifier.height(paddingMedium))
        state.beers?.let {
            val beers = it.collectAsLazyPagingItems()
            BeerList(beer = beers, onCLick = {
                navigateToDetailScreen(it)
            })
        }
    }

}