package dev.zaenur.jetpokemon.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zaenur.jetpokemon.data.PokemonRepository
import dev.zaenur.jetpokemon.model.Pokemon
import dev.zaenur.jetpokemon.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PokemonRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Pokemon>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Pokemon>>>
        get() = _uiState

    fun getAllPokemon() {
        viewModelScope.launch {
            repository.getAllPokemon()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}