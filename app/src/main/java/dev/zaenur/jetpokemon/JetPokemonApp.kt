package dev.zaenur.jetpokemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.zaenur.jetpokemon.ui.navigation.Screen
import dev.zaenur.jetpokemon.ui.screen.about.AboutScreen
import dev.zaenur.jetpokemon.ui.screen.detail.DetailScreen
import dev.zaenur.jetpokemon.ui.screen.home.HomeScreen
import dev.zaenur.jetpokemon.ui.theme.JetPokemonTheme

@Composable
fun JetPokemonApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                modifier = modifier,
                navigateToDetail = { pokemonId ->
                    navController.navigate(Screen.DetailPokemon.createRoute(pokemonId))
                }
            )
        }
        composable(Screen.About.route) {
            AboutScreen()
        }
        composable(
            route = Screen.DetailPokemon.route,
            arguments = listOf(navArgument("pokemonId"){type = NavType.IntType}),
        ){
            val id = it.arguments?.getInt("pokemonId") ?: 0
            DetailScreen(
                pokemonId = id
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