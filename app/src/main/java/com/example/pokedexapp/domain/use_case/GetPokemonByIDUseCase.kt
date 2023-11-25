package com.example.pokedexapp.domain.use_case

import com.example.pokedexapp.data.mapper.toPokemonDetail
import com.example.pokedexapp.data.remote.PokemonApi
import com.example.pokedexapp.domain.model.PokemonDetail
import com.example.pokedexapp.util.Resource
import javax.inject.Inject

class GetPokemonByIDUseCase @Inject constructor(
    private val pokemonApi: PokemonApi
) {
    suspend fun executeGetPokemonByIDUseCase(pokemonID: String): Resource<PokemonDetail> {
        return try {
            Resource.Success(
                data = pokemonApi.getPokemonByID(pokemonID).toPokemonDetail()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}