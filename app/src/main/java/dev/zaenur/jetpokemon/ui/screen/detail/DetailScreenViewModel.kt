package dev.zaenur.jetpokemon.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zaenur.jetpokemon.data.PokemonRepository
import dev.zaenur.jetpokemon.model.Pokemon
import dev.zaenur.jetpokemon.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val repository: PokemonRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Pokemon>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Pokemon>>
        get() = _uiState

    fun getPokemonById(pokemonId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getPokemonById(pokemonId))
        }
    }
}