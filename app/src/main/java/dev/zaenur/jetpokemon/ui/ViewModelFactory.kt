package dev.zaenur.jetpokemon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.zaenur.jetpokemon.data.PokemonRepository
import dev.zaenur.jetpokemon.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: PokemonRepository):
    ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
}