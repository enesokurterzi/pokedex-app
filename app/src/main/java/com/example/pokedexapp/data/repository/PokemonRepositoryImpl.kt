package com.example.pokedexapp.data.repository

import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.domain.model.PokemonAbout
import com.example.pokedexapp.domain.model.PokemonDetail
import com.example.pokedexapp.domain.repository.PokemonRepository
import com.example.pokedexapp.domain.use_case.GetAllPokemonsUseCase
import com.example.pokedexapp.domain.use_case.GetPokemonAboutByIDUseCase
import com.example.pokedexapp.domain.use_case.GetPokemonByIDUseCase
import com.example.pokedexapp.util.Resource
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase,
    private val getPokemonByIDUseCase: GetPokemonByIDUseCase,
    private val getPokemonAboutByIDUseCase: GetPokemonAboutByIDUseCase
) : PokemonRepository {

    override suspend fun getAllPokemons(): Resource<List<Pokemon>> {
        return getAllPokemonsUseCase.executeGetAllPokemons()
    }

    override suspend fun getPokemonByID(pokemonID: String): Resource<PokemonDetail> {
        return getPokemonByIDUseCase.executeGetPokemonByIDUseCase(pokemonID)
    }

    override suspend fun getPokemonAboutByID(pokemonID: String): Resource<PokemonAbout> {
        return getPokemonAboutByIDUseCase.executeGetPokemonAboutByIDUseCase(pokemonID)
    }

}