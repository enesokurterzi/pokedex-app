package com.example.pokedexapp.presentation.view.detail

import com.example.pokedexapp.domain.model.Pokemon
import java.io.Serializable

data class DetailPokemonListData(
    val pokemonList: List<Pokemon>
): Serializable