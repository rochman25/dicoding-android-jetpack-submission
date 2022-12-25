package dev.zaenur.jetpokemon

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zaenur.jetpokemon.ui.screen.home.HomeContent
import dev.zaenur.jetpokemon.ui.theme.JetPokemonTheme

@Composable
fun JetPokemonApp(
    modifier: Modifier = Modifier
) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(stringResource(id = R.string.app_name))
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            actions = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Person, null)
                }
            })

        HomeContent(modifier)
    }

}


@Preview(showBackground = true)
@Composable
fun JetPokemonAppPreview() {
    JetPokemonTheme {
        JetPokemonApp()
    }
}