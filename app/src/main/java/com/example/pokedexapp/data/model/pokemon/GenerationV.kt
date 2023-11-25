package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: com.example.pokedexapp.data.model.pokemon.BlackWhite
)