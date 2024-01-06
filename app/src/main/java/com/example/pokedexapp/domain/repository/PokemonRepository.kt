package com.example.pokedexapp.domain.repository

import com.example.pokedexapp.data.model.pokemon.PokemonDto
import com.example.pokedexapp.data.model.pokemon_list.PokemonListDto
import com.example.pokedexapp.data.model.pokemon_species.PokemonSpeciesDto

interface PokemonRepository {

    suspend fun getAllPokemons(): PokemonListDto

    suspend fun getPokemonByID(pokemonID: String): PokemonDto

    suspend fun getPokemonAboutByID(pokemonID: String): PokemonSpeciesDto

}