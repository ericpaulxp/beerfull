package com.example.beerfull.presentation.common

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.beerfull.R
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.paging.LoadState
import com.airbnb.lottie.compose.LottieConstants

import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error? = null) {

    val message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.drunk_bottle_animation))
    val composition1 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.search_lottie_animation))

    if (error == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(250.dp),
                iterations = LottieConstants.IterateForever
            )

            Text(
                modifier = Modifier.padding(10.dp),
                text = "You have not saved Any Beer so far !",
                style = MaterialTheme.typography.titleMedium,
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            )
        }
    }
    EmptyContent(composition = composition1, message = message)
}

@Composable
fun EmptyContent(
    composition: LottieComposition?, message: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = composition, modifier = Modifier.size(250.dp)
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = message,
            style = MaterialTheme.typography.titleMedium,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
        )
    }
}


@Composable
fun EmptyScreenSearch(
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.search_lottie_animation))

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(250.dp),
            iterations = LottieConstants.IterateForever
        )

        Text(
            modifier = Modifier
                .padding(10.dp),
            text = "No Beers Found!",
            style = MaterialTheme.typography.titleMedium,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
        )
    }


}

fun parseErrorMessage(error: LoadState.Error?): String {
    return when (error?.error) {
        is SocketTimeoutException -> {
            "Server Unavailable."
        }

        is ConnectException -> {
            "Internet Unavailable."
        }

        else -> {
            "No Beer Found.Try Again.."
        }
    }
}
