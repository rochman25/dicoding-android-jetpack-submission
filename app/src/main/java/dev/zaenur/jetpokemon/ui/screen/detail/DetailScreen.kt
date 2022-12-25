package dev.zaenur.jetpokemon.ui.screen.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.zaenur.jetpokemon.di.Injection
import dev.zaenur.jetpokemon.model.Pokemon
import dev.zaenur.jetpokemon.ui.ViewModelFactory
import dev.zaenur.jetpokemon.ui.common.UiState
import dev.zaenur.jetpokemon.ui.theme.JetPokemonTheme


@Composable
fun DetailScreen(
    pokemonId: Int,
    viewModel: DetailScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getPokemonById(pokemonId)
            }
            is UiState.Success -> {
                val data = uiState.data
                Column {
                    TopAppBar(
                        elevation = 4.dp,
                        title = {
                            Text(
                                stringResource(id = dev.zaenur.jetpokemon.R.string.app_name),
                                color = Color.White,
                            )
                        },
                        backgroundColor = Color(android.graphics.Color.parseColor(data.color)),
                        actions = {
                            IconButton(onClick = {

                            }) {
                                Text(
                                    text = "#%03d".format(pokemonId),
                                    color = Color.White,
                                )
                            }
                        }
                    )

                    DetailContent(
                        pokemon = data,
                    )
                }
            }
            is UiState.Error -> {}
        }
    }


}

@Composable
fun DetailContent(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(color = Color.DarkGray)
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        bottomStart = 96.dp,
                        bottomEnd = 96.dp,
                    )
                )
                .background(color = Color(android.graphics.Color.parseColor(pokemon.color)))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = pokemon.image,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            bottom = 16.dp,
                        )
                        .size(200.dp)
                )

            }

        }

        Text(
            text = pokemon.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(
                modifier = modifier.width(200.dp)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = pokemon.weight.uppercase(),
                    color = Color.White,
                    fontSize = 24.sp,
                    modifier = modifier.fillMaxWidth(),
                )
                Text(
                    text = "Weight",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth(),
                )
            }
            Column(
                modifier = modifier.width(200.dp)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = pokemon.height.uppercase(),
                    color = Color.White,
                    fontSize = 24.sp,
                    modifier = modifier.fillMaxWidth(),
                )
                Text(
                    text = "Height",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth(),
                )
            }
        }

        Text(
            text = "Description",
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
            modifier = modifier.fillMaxWidth().padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
            ),
        )

        Text(
            text = pokemon.description,
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier.fillMaxWidth().padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp,
            ),
        )

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    JetPokemonTheme {
        DetailContent(
            Pokemon(
                1,
                "Bulbasaur",
                "Seed Pokémon",
                "0.7 m",
                "6.9 kg",
                "https://raw.githubusercontent.com/Purukitto/pokemon-data.json/master/images/pokedex/hires/001.png",
                "https://raw.githubusercontent.com/Purukitto/pokemon-data.json/master/images/pokedex/thumbnails/001.png",
                "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun’s rays, the seed grows progressively larger.",
                "#68a891"
            )
        )
    }
}