package dev.zaenur.jetpokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.zaenur.jetpokemon.data.PokemonData
import dev.zaenur.jetpokemon.ui.theme.JetPokemonTheme

@Composable
fun JetPokemonApp(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(144.dp),
        content = {
            items(PokemonData.pokemon, key = { it.id }) { pokemon ->
                PokemonListItem(
                    name = pokemon.name,
                    thumbnail = pokemon.thumbnail,
                    color = pokemon.color,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
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
            modifier = modifier.clickable { }
        ) {
            AsyncImage(
                model = thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(60.dp)
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

@Preview(showBackground = true)
@Composable
fun JetPokemonAppPreview() {
    JetPokemonTheme {
        JetPokemonApp()
    }
}