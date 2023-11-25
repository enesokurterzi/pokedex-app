package com.example.pokedexapp.data.mapper

import com.example.pokedexapp.data.model.pokemon_list.Result
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.util.Constants.BASE_IMAGE_URL

fun Result.toPokemon(): Pokemon {
    val id = url.trim('/').split("/").last()
    val imageUrl = "$BASE_IMAGE_URL$id.png"
    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl
    )

}