package com.example.beerfull.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.beerfull.R
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.ui.theme.BeerFullTheme
import com.example.beerfull.utils.Dimens
import com.example.beerfull.utils.Dimens.beerImageSize

@Composable
fun BeerCard(
    modifier: Modifier = Modifier,
    beer: Beer,
    onClick: (() -> Unit)? = null,

    ) {
    Row(
        modifier = modifier
            .clickable { onClick?.invoke() }
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(Dimens.paddingMedium),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.White)
                .clip(MaterialTheme.shapes.medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(beerImageSize),
                model = beer.image_url,
                placeholder = painterResource(id = R.drawable.beer_placeholder),
                contentDescription = "",
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = beer.name,
                maxLines = 2,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = beer.tagline,
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = beer.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "First Brewed in ${beer.first_brewed}",
                textAlign = TextAlign.End,
                fontSize = 12.sp
            )

        }


    }

}

@Preview
@Composable
fun BeerCardPreview() {
    BeerFullTheme {
        BeerCard(
            beer = Beer(
                id = 192, name = "Punk IPA 2007 - 2010",
                tagline = "Post Modern Classic. Spiky. Tropical. Hoppy.",
                first_brewed = "04/2007",
                description = "Our flagship beer that kick started the craft beer revolution. This is James and Martin's original take on an American IPA, subverted with punchy New Zealand hops. Layered with new world hops to create an all-out riot of grapefruit, pineapple and lychee before a spiky, mouth-puckering bitter finish.",
                ibu = 60.0,
                brewers_tips = "While it may surprise you, this version of Punk IPA isn't dry hopped but still packs a punch! To make the best of the aroma hops make sure they are fully submerged and add them just before knock out for an intense hop hit.",
                image_url = " https://images.punkapi.com/v2/192.png",
                contributed_by = "ali",
                ebc = 40.0,
                ph = 7.0,
            )
        )
    }
}