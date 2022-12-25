package dev.zaenur.jetpokemon.di

import dev.zaenur.jetpokemon.data.PokemonRepository

object Injection {
    fun provideRepository(): PokemonRepository {
        return PokemonRepository.getInstance()
    }
}