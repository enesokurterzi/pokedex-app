package com.example.pokedexapp.domain.use_case

import com.example.pokedexapp.data.mapper.toPokemon
import com.example.pokedexapp.data.remote.PokemonApi
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.util.Resource
import javax.inject.Inject

class GetAllPokemonsUseCase @Inject constructor(
    private val pokemonApi: PokemonApi
) {
    suspend fun executeGetAllPokemons () : Resource<List<Pokemon>> {
        return try {
            Resource.Success(
                data = pokemonApi.getAllPokemons().results.map { it.toPokemon() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
//            Resource.Error(e.message ?: "An unknown error occurred.")
            Resource.Error("Please check your internet connection.")
        }
    }
}