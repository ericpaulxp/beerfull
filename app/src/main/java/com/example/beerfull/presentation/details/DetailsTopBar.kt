package com.example.beerfull.presentation.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.beerfull.R
import com.example.beerfull.utils.Dimens.iconSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onFavoriteClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = ""
                )
            }
        },
        actions = {

            IconButton(onClick = onFavoriteClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = ""
                )
            }
            IconButton(onClick = onShareClicked) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = ""
                )
            }
        }
    )
}