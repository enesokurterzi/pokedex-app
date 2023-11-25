package com.example.pokedexapp.domain.use_case

import com.example.pokedexapp.data.mapper.toPokemon
import com.example.pokedexapp.data.mapper.toPokemonDetail
import com.example.pokedexapp.data.remote.PokemonApi
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.domain.model.PokemonAbout
import com.example.pokedexapp.util.Resource
import javax.inject.Inject

class GetPokemonAboutByIDUseCase @Inject constructor(
    private val pokemonApi: PokemonApi
) {
    suspend fun executeGetPokemonAboutByIDUseCase (pokemonID: String) : Resource<PokemonAbout> {
        return try {
            Resource.Success(
                data = pokemonApi.getPokemonAboutByID(pokemonID).toPokemonDetail()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}