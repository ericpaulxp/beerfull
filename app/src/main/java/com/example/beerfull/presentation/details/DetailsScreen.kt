package com.example.beerfull.presentation.details

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.ui.theme.BeerFullTheme
import com.example.beerfull.utils.Dimens.beerImageSize
import com.example.beerfull.utils.Dimens.paddingExtraSmall
import com.example.beerfull.utils.Dimens.paddingMedium
import com.example.beerfull.utils.Dimens.paddingSmall
import com.example.beerfull.utils.UIComponent

@Composable
fun DetailsScreen(
    beer: Beer,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
    sideEffect: UIComponent?
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when (sideEffect) {
                is UIComponent.Toast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onFavoriteClicked = {
                event(DetailsEvent.UpsertDeleteFavoriteBeer(beer))
            },
            onShareClicked = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, beer.image_url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = paddingMedium,
                end = paddingMedium,
                top = paddingMedium,
            )
        ) {
            item {
                Row {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = paddingSmall, bottom = paddingSmall)
                            .background(Color.White),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        AsyncImage(
                            model = beer.image_url,
                            contentDescription = "",
                            modifier = Modifier
                                .height(beerImageSize)
                        )
                    }
                    Spacer(modifier = Modifier.width(paddingMedium))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f),
                    ) {
                        Text(
                            text = beer.name,
                            style = MaterialTheme.typography.displaySmall,
                            maxLines = 3
                        )
                        Text(
                            text = beer.tagline,
                            fontStyle = FontStyle.Italic,
                            maxLines = 2,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(paddingMedium))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Absolute.spacedBy(24.dp)
                        ) {
                            Card(
                                shape = MaterialTheme.shapes.small
                            ) {
                                Text(
                                    modifier = Modifier.padding(paddingExtraSmall),
                                    text = "Ibu: " + beer.ibu,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                            Card(
                                shape = MaterialTheme.shapes.small,
                            ) {
                                Text(
                                    modifier = Modifier.padding(paddingExtraSmall),
                                    text = "Ph: " + beer.ph,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                            Card(
                                shape = MaterialTheme.shapes.small,
                            ) {
                                Text(
                                    modifier = Modifier.padding(paddingExtraSmall),
                                    text = "Ebc: " + beer.ebc,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(paddingSmall))


                        Text(
                            text = "First Brewed: " + beer.first_brewed,
                            style = MaterialTheme.typography.bodyLarge,
                        )

                    }
                }
                Spacer(modifier = Modifier.height(paddingMedium))
                Text(
                    text = "Description: " + beer.description,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(paddingMedium))

                Card(
                    shape = MaterialTheme.shapes.small,
                    elevation = CardDefaults.cardElevation(paddingExtraSmall)
                ) {
                    Text(
                        modifier = Modifier.padding(paddingSmall),
                        text = "Brewer Tips: " + beer.brewers_tips,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Spacer(modifier = Modifier.height(paddingMedium))

                Text(
                    text = "By: " + beer.contributed_by,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    BeerFullTheme {
        DetailsScreen(
            beer = Beer(
                brewers_tips = "While it may surprise you, this version of Punk IPA isn't dry hopped but still packs a punch! To make the best of the aroma hops make sure they are fully submerged and add them just before knock out for an intense hop hit.",
                contributed_by = "Sam Mason <stonemason>",
                description = "Our flagship beer that kick started the craft beer revolution. This is James and Martin's original take on an American IPA, subverted with punchy New Zealand hops. Layered with new world hops to create an all-out riot of grapefruit, pineapple and lychee before a spiky, mouth-puckering bitter finish.",
                ebc = 17.0,
                first_brewed = "04/2007",
                ibu = 60.0,
                id = 193,
                image_url = "https://images.punkapi.com/v2/192.png",
                name = "Punk IPA 2007 - 2010",
                ph = 7.0,
                tagline = "Post Modern Classic. Spiky. Tropical. Hoppy.",
            ), event = {},
            sideEffect = null,
            navigateUp = {}
        )
    }
}
