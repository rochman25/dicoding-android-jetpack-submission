package dev.zaenur.jetpokemon.data

import dev.zaenur.jetpokemon.model.Pokemon
import dev.zaenur.jetpokemon.model.PokemonData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PokemonRepository {
    private val pokemonData = mutableListOf<Pokemon>()

    init {
        if (pokemonData.isEmpty()) {
            PokemonData.pokemon.forEach {
                pokemonData.add(it)
            }
        }
    }

    fun getAllPokemon(): Flow<List<Pokemon>> {
        return flowOf(pokemonData)
    }

    fun getPokemonById(pokemonId: Int): Pokemon {
        return pokemonData.first {
            it.id == pokemonId
        }
    }

    companion object {
        @Volatile
        private var instance: PokemonRepository? = null

        fun getInstance(): PokemonRepository =
            instance ?: synchronized(this) {
                PokemonRepository().apply {
                    instance = this
                }
            }
    }
}