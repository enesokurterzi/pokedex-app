package com.example.pokedexapp.data.model.pokemon_list

data class PokemonListDto(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)