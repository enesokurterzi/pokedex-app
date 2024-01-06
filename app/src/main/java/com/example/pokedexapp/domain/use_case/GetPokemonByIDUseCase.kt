package com.example.pokedexapp.domain.use_case

import com.example.pokedexapp.data.mapper.toPokemonDetail
import com.example.pokedexapp.domain.model.PokemonDetail
import com.example.pokedexapp.domain.repository.PokemonRepository
import com.example.pokedexapp.util.Resource
import javax.inject.Inject

class GetPokemonByIDUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemonID: String): Resource<PokemonDetail> {
        return try {
            Resource.Success(
                data = pokemonRepository.getPokemonByID(pokemonID).toPokemonDetail()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}