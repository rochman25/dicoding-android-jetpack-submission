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
import androidx.compose.runtime.collectAsState
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
import dev.zaenur.jetpokemon.di.Injection
import dev.zaenur.jetpokemon.model.Pokemon
import dev.zaenur.jetpokemon.ui.ViewModelFactory
import dev.zaenur.jetpokemon.ui.common.UiState


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
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
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uistate ->
            when(uistate) {
                is UiState.Loading -> {
                    viewModel.getAllPokemon()
                }
                is UiState.Success -> {
                    HomeContent(
                        pokemonData = uistate.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail
                    )
                }
                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun HomeContent(
    pokemonData: List<Pokemon>,
    modifier : Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(144.dp),
        content = {
            items(pokemonData) { data ->
                PokemonListItem(
                    name = data.name,
                    thumbnail = data.thumbnail,
                    color = data.color,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            navigateToDetail(data.id)
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