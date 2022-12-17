package dev.zaenur.jetpokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.zaenur.jetpokemon.data.PokemonData
import dev.zaenur.jetpokemon.ui.theme.JetPokemonTheme

@Composable
fun JetPokemonApp(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier) {
      LazyColumn {
          items(PokemonData.pokemon, key = {it.id}) { pokemon ->
              PokemonListItem(name = pokemon.name, thumbmail = pokemon.thumbnail, modifier = Modifier.fillMaxWidth())
          }
      }
    }
}

@Composable
fun PokemonListItem(
    name: String,
    thumbmail: String,
    modifier: Modifier = Modifier
){
    Row(
       verticalAlignment = Alignment.CenterVertically,
       modifier = modifier.clickable {  }
    ) {
        AsyncImage(
            model = thumbmail,
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
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JetPokemonAppPreview() {
    JetPokemonTheme {
        JetPokemonApp()
    }
}