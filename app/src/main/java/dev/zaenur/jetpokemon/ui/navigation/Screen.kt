package dev.zaenur.jetpokemon.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailPokemon : Screen("home/{pokemonId}") {
        fun createRoute(pokemonId: Int) = "home/$pokemonId"
    }
}
