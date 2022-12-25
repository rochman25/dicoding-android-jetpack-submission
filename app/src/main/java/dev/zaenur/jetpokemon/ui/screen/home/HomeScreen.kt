package dev.zaenur.jetpokemon.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.zaenur.jetpokemon.model.PokemonData


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(stringResource(id = dev.zaenur.jetpokemon.R.string.app_name))
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            actions = {
                IconButton(onClick = {

                }) {
                    Icon(Icons.Filled.Person, null)
                }
            }
        )

        HomeContent(
            modifier = modifier,
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
fun HomeContent(
    modifier : Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(144.dp),
        content = {
            items(PokemonData.pokemon, key = { it.id }) { pokemon ->
                PokemonListItem(
                    name = pokemon.name,
                    thumbnail = pokemon.thumbnail,
                    color = pokemon.color,
                    modifier = modifier.fillMaxWidth().clickable {
                        navigateToDetail(pokemon.id)
                    }
                )
            }
        },
        modifier = modifier.padding(
            16.dp
        )
    )
}

@Composable
fun PokemonListItem(
    name: String,
    thumbnail: String,
    color: String,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = Color(android.graphics.Color.parseColor(color)),
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            AsyncImage(
                model = thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(96.dp)
                    .clip(CircleShape)
            )
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        8.dp
                    )
            )
        }
    }
}