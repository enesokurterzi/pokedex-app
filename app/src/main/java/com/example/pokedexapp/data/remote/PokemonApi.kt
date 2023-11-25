package com.example.pokedexapp.data.remote

import com.example.pokedexapp.data.model.pokemon.PokemonDto
import com.example.pokedexapp.data.model.pokemon_list.PokemonListDto
import com.example.pokedexapp.data.model.pokemon_species.PokemonSpeciesDto
import com.example.pokedexapp.domain.model.PokemonAbout
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon/{id}")
    suspend fun getPokemonByID(
        @Path("id") id: String
    ): PokemonDto

    @GET("pokemon-species/{id}")
    suspend fun getPokemonAboutByID(
        @Path("id") id: String
    ): PokemonSpeciesDto

    @GET("pokemon?limit=10000")
    suspend fun getAllPokemons(): PokemonListDto

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): PokemonDto

    @GET("pokemon?limit=100&offset=0")
    suspend fun getFirst100Pokemons(): PokemonListDto


}