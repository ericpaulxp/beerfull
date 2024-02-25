package com.example.beerfull.presentation.beer_navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.beerfull.utils.Dimens.iconSize
import com.example.beerfull.utils.Dimens.paddingExtraSmall

@Composable
fun BeerBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEachIndexed { index, items ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = items.icon),
                            contentDescription = "",
                            modifier = Modifier.size(iconSize)
                        )
                        Spacer(modifier = Modifier.height(paddingExtraSmall))
                        Text(text = items.text, style = MaterialTheme.typography.labelSmall)
                    }
                }
            )
        }
    }
}


data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)