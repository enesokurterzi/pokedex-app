package com.example.pokedexapp.data.mapper

import com.example.pokedexapp.data.model.pokemon_species.PokemonSpeciesDto
import com.example.pokedexapp.domain.model.PokemonAbout

fun PokemonSpeciesDto.toPokemonDetail(): PokemonAbout {
    return PokemonAbout(
        about = aboutTextCleaner(flavorTextEntries.first().flavorText)
    )
}

fun aboutTextCleaner(aboutText: String): String {
    return aboutText.replace("\n", " ")
}