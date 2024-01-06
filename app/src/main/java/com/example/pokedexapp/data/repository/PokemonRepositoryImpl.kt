package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.model.pokemon.PokemonDto
import com.example.pokedexapp.data.model.pokemon_list.PokemonListDto
import com.example.pokedexapp.data.model.pokemon_species.PokemonSpeciesDto
import com.example.pokedexapp.data.remote.PokemonApi
import com.example.pokedexapp.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {

    override suspend fun getAllPokemons(): PokemonListDto {
        return api.getAllPokemons()
    }

    override suspend fun getPokemonByID(pokemonID: String): PokemonDto {
        return api.getPokemonByID(pokemonID)
    }

    override suspend fun getPokemonAboutByID(pokemonID: String): PokemonSpeciesDto {
        return api.getPokemonAboutByID(pokemonID)
    }

}