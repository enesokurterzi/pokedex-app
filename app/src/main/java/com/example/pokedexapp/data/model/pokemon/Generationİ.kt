package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class Generationİ(
    @SerializedName("red-blue")
    val redBlue: com.example.pokedexapp.data.model.pokemon.RedBlue,
    @SerializedName("yellow")
    val yellow: com.example.pokedexapp.data.model.pokemon.Yellow
)