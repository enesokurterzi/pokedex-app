package com.example.pokedexapp.domain.repository

import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.domain.model.PokemonAbout
import com.example.pokedexapp.domain.model.PokemonDetail
import com.example.pokedexapp.util.Resource

interface PokemonRepository {

    suspend fun getAllPokemons(): Resource<List<Pokemon>>

    suspend fun getPokemonByID(pokemonID: String): Resource<PokemonDetail>

    suspend fun getPokemonAboutByID(pokemonID: String): Resource<PokemonAbout>

}